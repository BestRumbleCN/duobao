package team.wuxie.crowdfunding.service.impl;

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

import team.wuxie.crowdfunding.domain.enums.BidStatus;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.TTrade;
import team.wuxie.crowdfunding.domain.enums.TradeSource;
import team.wuxie.crowdfunding.domain.enums.TradeStatus;
import team.wuxie.crowdfunding.domain.enums.TradeType;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.mapper.TShoppingLogMapper;
import team.wuxie.crowdfunding.mapper.TTradeMapper;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.ro.order.OrderRO.InnerGoods;
import team.wuxie.crowdfunding.service.TradeService;
import team.wuxie.crowdfunding.util.IdGenerator;
import team.wuxie.crowdfunding.util.aliyun.alipay.AliPayService;
import team.wuxie.crowdfunding.util.redis.RedisConstant;
import team.wuxie.crowdfunding.util.redis.RedisHelper;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * ClassName:TradeServiceImpl <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月15日 上午12:04:21
 * @see
 */
@Service
public class TradeServiceImpl extends AbstractService<TTrade> implements TradeService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	TGoodsBidMapper goodsBidMapper;

	@Autowired
	TShoppingLogMapper shoppingLogMapper;

	@Autowired
	TTradeMapper tradeMapper;

	@Override
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
	public void purchase(OrderRO orderRo, Integer userId, TradeSource tradeSource) throws IllegalArgumentException {
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
			for (InnerGoods innerGood : innerGoods) {
				RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + innerGood.getBidId(), -innerGood.getAmount());
			}
			throw e;
		}
		switch (tradeSource) {
		case ALIPAY:
			TTrade trade = new TTrade(null, userId, IdGenerator.generateTradeNo(userId), tradeSource,
					TradeStatus.WAITTING, TradeType.GOODS, "众筹夺宝", JSON.toJSONString(orderRo), "众筹夺宝", null,
					total.toString(), null, null);
			this.insertSelective(trade);
		default:
			break;
		}
	}

	/**
	 * TODO 支付回调
	 * 
	 * @author fly
	 * @param tradeNo
	 * @since
	 */
	@Transactional
	private void tradeCallBack(String tradeNo) {
		LOGGER.info("TRADE CALLBACK START {}", tradeNo);
		TTrade trade = tradeMapper.selectByTradeNo(tradeNo);
		Assert.notNull(trade, "不存在的交易");
		trade.setTradeStatus(TradeStatus.SUCCESS);
		tradeMapper.updateByPrimaryKeySelective(trade);
		OrderRO orderRo = JSON.parseObject(trade.getTradeInfo(), OrderRO.class);
		List<InnerGoods> innerGoodsList = orderRo.getGoodsList();
		for (InnerGoods innerGoods : innerGoodsList) {
			Integer bidId = innerGoods.getBidId();
			goodsBidMapper.addJoinAmount(innerGoods.getAmount(), bidId);
		}
		//TODO 
		LOGGER.info("TRADE CALLBACK END {}", tradeNo);
	}

}