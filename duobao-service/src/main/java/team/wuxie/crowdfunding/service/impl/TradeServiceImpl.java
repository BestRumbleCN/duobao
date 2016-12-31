package team.wuxie.crowdfunding.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;

import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.TShoppingLog;
import team.wuxie.crowdfunding.domain.TTrade;
import team.wuxie.crowdfunding.domain.enums.BidStatus;
import team.wuxie.crowdfunding.domain.enums.TradeSource;
import team.wuxie.crowdfunding.domain.enums.TradeStatus;
import team.wuxie.crowdfunding.domain.enums.TradeType;
import team.wuxie.crowdfunding.exception.TradeException;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.mapper.TGoodsMapper;
import team.wuxie.crowdfunding.mapper.TShoppingLogMapper;
import team.wuxie.crowdfunding.mapper.TTradeMapper;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.ro.order.OrderRO.InnerGoods;
import team.wuxie.crowdfunding.service.TradeService;
import team.wuxie.crowdfunding.util.HttpUtils;
import team.wuxie.crowdfunding.util.IdGenerator;
import team.wuxie.crowdfunding.util.aliyun.alipay.AliPayService;
import team.wuxie.crowdfunding.util.date.DateFormatUtils;
import team.wuxie.crowdfunding.util.date.DateUtils;
import team.wuxie.crowdfunding.util.redis.RedisConstant;
import team.wuxie.crowdfunding.util.redis.RedisHelper;
import team.wuxie.crowdfunding.util.service.AbstractService;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.WePayUtil;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.PaymentNotification;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.WechatAppPayRequest;

/**
 * ClassName:TradeServiceImpl <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月15日 上午12:04:21
 * @see
 */
@Service
@Transactional(readOnly = true)
public class TradeServiceImpl extends AbstractService<TTrade> implements TradeService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	private TGoodsBidMapper goodsBidMapper;
	@Autowired
	private TShoppingLogMapper shoppingLogMapper;
	@Autowired
	private TTradeMapper tradeMapper;
	@Autowired
	private TGoodsMapper goodsMapper;

	@Override
	@Transactional
	public String recharge(float amount, Integer userId, TradeSource tradeSource) throws IllegalArgumentException {
		Assert.isTrue(amount > 0, "充值金额必须大于零");
		switch (tradeSource) {
		case ALIPAY:
			TTrade trade = new TTrade(null, userId, IdGenerator.generateTradeNo(userId), tradeSource,
					TradeStatus.WAITTING, TradeType.STAMPS, "点劵充值", null, "点劵充值", null, "0.01", null, null);
			this.insertSelective(trade);
			return AliPayService.generatePathParams(trade);
		default:
			break;
		}
		return null;
	}

	@Override
	@Transactional
	public WechatAppPayRequest purchase(OrderRO orderRo, Integer userId) throws IllegalArgumentException,
			TradeException {
		List<InnerGoods> innerGoods = orderRo.getGoodsList();
		Assert.notEmpty(innerGoods, "购物车为空！");
		// 0.校验数据正确性
		Map<Integer, TGoodsBid> bidMap = new HashMap<Integer, TGoodsBid>();
		Integer total = 0;
		for (InnerGoods innerGood : innerGoods) {
			Integer bidId = innerGood.getBidId();
			TGoodsBid goodsBid = goodsBidMapper.selectByPrimaryKey(bidId);
			Assert.notNull(goodsBid, "不存在的商品");
			Assert.isTrue(goodsBid.getBidStatus().equals(BidStatus.RUNNING), "本期商品已结束或已下架");
			Assert.isTrue(innerGood.getAmount() + goodsBid.getJoinAmount() <= goodsBid.getTotalAmount(), "本期商品余量不足");
			bidMap.put(bidId, goodsBid);
			total += innerGood.getAmount() * goodsBid.getSinglePrice();
		}
		Assert.isTrue(orderRo.getTotalCost() == total, "订单总金额不正确，请重新计算");

		// 1.将所有商品欲购买数量都先加上，方便出错时回滚
		Map<Integer, Integer> bidPurchaseNum = new HashMap<Integer, Integer>();
		for (InnerGoods innerGood : innerGoods) {
			Integer bidId = innerGood.getBidId();
			bidPurchaseNum.put(innerGood.getBidId(),
					RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + bidId, innerGood.getAmount()));
		}
		// 2.校验数量
		try {
			for (Integer bidId : bidMap.keySet()) {
				TGoodsBid goodsBid = bidMap.get(bidId);
				Assert.isTrue(bidPurchaseNum.get(bidId) <= goodsBid.getTotalAmount(), "本期商品余量不足");
			}
		} catch (IllegalArgumentException e) {
			rollbackRedis(innerGoods);
			throw e;
		}
		try {
			// 生成订单号
			Integer wbNoCount = RedisHelper.incr(RedisConstant.TRADE_NO_SUF + DateFormatUtils.dateFormat(new Date()));
			if (wbNoCount > 999999 || wbNoCount < 100001) {
				wbNoCount = 100001;
				RedisHelper.set(RedisConstant.TRADE_NO_SUF + DateFormatUtils.dateFormat(new Date()), wbNoCount);
			}
			String wayBillNo = DateFormatUtils.dateFormat(new Date()) + wbNoCount;

			TTrade trade = new TTrade(null, userId, wayBillNo, TradeSource.WEIXIN, TradeStatus.WAITTING,
					TradeType.GOODS, "众筹夺宝", JSON.toJSONString(orderRo), "众筹夺宝", null, total.toString(), null, null);
			this.insertSelective(trade);
			return WePayUtil.getAppPayRequest(orderRo, bidMap, wayBillNo);
		} catch (Exception e) {
			rollbackRedis(innerGoods);
			throw e;
		}
	}

	private void rollbackRedis(List<InnerGoods> innerGoods) {
		for (InnerGoods innerGood : innerGoods) {
			RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + innerGood.getBidId(), -innerGood.getAmount());
		}
	}

	@Override
	@Transactional
	public synchronized void weixinPayCallback(PaymentNotification notification) throws TradeException {
		// 1.校验签名
		boolean signValid = WePayUtil.validatePayNotiSign(notification);
		if (!signValid) {
			throw new TradeException("invalid sign");
		}
		String tradeNo = notification.getOut_trade_no();
		LOGGER.info("TRADE CALLBACK START {}", tradeNo);
		LOGGER.info("notification value :" + notification);
		TTrade trade = tradeMapper.selectByTradeNo(tradeNo);
		Assert.notNull(trade, "不存在的交易"+tradeNo);
		if (TradeStatus.WAITTING != trade.getTradeStatus()) {
			return;
		}
		if ("FAIL".equals(notification.getReturn_code()) || "FAIL".equals(notification.getResult_code())) {
			LOGGER.warn("支付失败" + notification.getOut_trade_no());
			return;
		}
		trade.setTradeStatus(TradeStatus.SUCCESS);
		tradeMapper.updateByPrimaryKeySelective(trade);
		OrderRO orderRo = JSON.parseObject(trade.getTradeInfo(), OrderRO.class);
		List<InnerGoods> innerGoodsList = orderRo.getGoodsList();
		for (InnerGoods innerGoods : innerGoodsList) {
			Integer bidId = innerGoods.getBidId();
			// goodsBidMapper.addJoinAmount(innerGoods.getAmount(), bidId);
			TGoodsBid goodsBid = goodsBidMapper.selectByPrimaryKey(bidId);
			StringBuilder bidNums = new StringBuilder("");
			for (int i = 1; i <= innerGoods.getAmount(); i++) {
				bidNums.append(10000000 + i + goodsBid.getJoinAmount()).append(",");
			}
			TShoppingLog shoppingLog = new TShoppingLog(null, trade.getUserId(), bidId, innerGoods.getAmount(),
					goodsBid.getGoodsId(), bidNums.toString(), orderRo.getIp(), HttpUtils.getCityByIp(orderRo.getIp()),
					false, null, null);
			shoppingLogMapper.insertSelective(shoppingLog);
			Integer totalAmount = innerGoods.getAmount() + goodsBid.getJoinAmount();
			goodsBid.setJoinAmount(totalAmount);
			if (totalAmount == goodsBid.getTotalAmount()) {
				// 放入待揭晓
				goodsBid.setBidStatus(BidStatus.UNPUBLISHED);
				goodsBid.setPublishTime(DateUtils.addMinutes(new Date(), 5));
				TGoods goods = goodsMapper.selectByPrimaryKey(goodsBid.getGoodsId());
				TGoodsBid bid = new TGoodsBid(null, goods.getGoodsId(), goods.getTotalAmount(), 0, BidStatus.RUNNING, null,
						null, null, null, null, goods.getSinglePrice());
				goodsBidMapper.insertSelective(bid);
			}
			goodsBidMapper.updateByPrimaryKeySelective(goodsBid);
			
		}
		// TODO
		LOGGER.info("TRADE CALLBACK END {}", tradeNo);
	}
}
