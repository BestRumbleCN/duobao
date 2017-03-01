package team.wuxie.crowdfunding.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;

import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.domain.TShoppingLog;
import team.wuxie.crowdfunding.domain.TTrade;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.domain.enums.BidStatus;
import team.wuxie.crowdfunding.domain.enums.MessageType;
import team.wuxie.crowdfunding.domain.enums.TradeSource;
import team.wuxie.crowdfunding.domain.enums.TradeStatus;
import team.wuxie.crowdfunding.domain.enums.TradeType;
import team.wuxie.crowdfunding.exception.TradeException;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.mapper.TGoodsMapper;
import team.wuxie.crowdfunding.mapper.TShoppingCartMapper;
import team.wuxie.crowdfunding.mapper.TShoppingLogMapper;
import team.wuxie.crowdfunding.mapper.TTradeMapper;
import team.wuxie.crowdfunding.mapper.TUserMapper;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.ro.order.OrderRO.InnerGoods;
import team.wuxie.crowdfunding.service.MessageService;
import team.wuxie.crowdfunding.service.TradeService;
import team.wuxie.crowdfunding.util.HttpUtils;
import team.wuxie.crowdfunding.util.aliyun.alipay.AliPayService;
import team.wuxie.crowdfunding.util.date.DateFormatUtils;
import team.wuxie.crowdfunding.util.date.DateUtils;
import team.wuxie.crowdfunding.util.redis.RedisConstant;
import team.wuxie.crowdfunding.util.redis.RedisHelper;
import team.wuxie.crowdfunding.util.service.AbstractService;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.WePayUtil;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.PaymentNotification;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.WechatAppPayRequest;
import team.wuxie.crowdfunding.vo.TradeDonateVO;

/**
 * ClassName:TradeServiceImpl <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月15日 上午12:04:21
 * @see
 */
@Service
@Transactional
public class TradeServiceImpl extends AbstractService<TTrade>implements TradeService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	private TGoodsBidMapper goodsBidMapper;
	@Autowired
	private TShoppingLogMapper shoppingLogMapper;
	@Autowired
	private TTradeMapper tradeMapper;
	@Autowired
	private TGoodsMapper goodsMapper;
	@Autowired
	private TShoppingCartMapper shoppingCartMapper;
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private MessageService messageService;

	@Override
	@Transactional
	public WechatAppPayRequest weixinRecharge(Integer amount, Integer userId, String ip)
			throws IllegalArgumentException, TradeException {
		Assert.isTrue(amount > 0, "充值金额必须大于零");
		String waybillNo = generateWbNo();
		TTrade trade = new TTrade(null, userId, waybillNo, TradeSource.WEIXIN, TradeStatus.WAITTING, TradeType.STAMPS,
				"众筹夺宝", String.format("amount={},userId={},ip={}", amount, userId, ip), "众筹夺宝", null, amount.toString(),
				null, null);
		this.insertSelective(trade);
		return WePayUtil.getAppPayRequest(amount, waybillNo, ip);
	}
	
	@Override
	@Transactional
	public Map<String,String> alipayRecharge(Integer amount, Integer userId, String ip) {
		Assert.isTrue(amount > 0, "充值金额必须大于零");
		String waybillNo = generateWbNo();
		TTrade trade = new TTrade(null, userId, waybillNo, TradeSource.ALIPAY, TradeStatus.WAITTING, TradeType.STAMPS,
				"众筹夺宝", String.format("amount={},userId={},ip={}", amount, userId, ip), "众筹夺宝", null, amount.toString(),
				null, null);
		this.insertSelective(trade);
		Map<String,String> result = new HashMap<String,String>();
		result.put("payStr", AliPayService.generatePathParams(trade));
		result.put("tradeNo", trade.getTradeNo());
		return result;
	}

	@Override
	@Transactional
	public WechatAppPayRequest donate(Integer amount, Integer userId, String ip)
			throws IllegalArgumentException, TradeException {
		Assert.isTrue(amount > 0, "捐赠金额必须大于零");
		String waybillNo = generateWbNo();
		TTrade trade = new TTrade(null, userId, waybillNo, TradeSource.WEIXIN, TradeStatus.WAITTING, TradeType.DONATE,
				"爱心捐赠", String.format("amount={},userId={},ip={}", amount, userId, ip), "希望树", null, amount.toString(),
				null, null);
		this.insertSelective(trade);
		return WePayUtil.getAppPayRequest(amount, waybillNo, ip);
	}

	@Override
	@Transactional
	public WechatAppPayRequest weixinPurchase(OrderRO orderRo, Integer userId)
			throws IllegalArgumentException, TradeException {
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
		// total += orderRo.getCoinPay();
		Assert.isTrue(orderRo.getTotalCost() + orderRo.getCoinPay() == total.intValue(), "订单总金额不正确，请重新计算");
		if (orderRo.getCoinPay() > 0) {
//			Integer lockedCoid = RedisHelper.incr(String.format(RedisConstant.LOCK_COIN_PRE, userId), 0);
			TUser user = userMapper.selectByPrimaryKey(userId);
			Assert.isTrue(orderRo.getCoinPay() <= user.getCoin().intValue(), "抢币不足！");
		}
		// 1.将所有商品欲购买数量都先加上，方便出错时回滚
		Map<Integer, Integer> bidPurchaseNum = new HashMap<Integer, Integer>();
		for (InnerGoods innerGood : innerGoods) {
			Integer bidId = innerGood.getBidId();
			bidPurchaseNum.put(innerGood.getBidId(),
					RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + bidId, innerGood.getAmount()));
		}
		//
		if (orderRo.getCoinPay() > 0 && orderRo.getTotalCost() == 0) {
			RedisHelper.incr(String.format(RedisConstant.LOCK_COIN_PRE, userId), orderRo.getCoinPay());
		}
		// 2.校验数量
		try {
			for (Integer bidId : bidMap.keySet()) {
				TGoodsBid goodsBid = bidMap.get(bidId);
				Assert.isTrue(bidPurchaseNum.get(bidId) <= goodsBid.getTotalAmount(), "本期商品余量不足");
			}
		} catch (IllegalArgumentException e) {
			rollbackRedis(orderRo, userId);
			throw e;
		}
		try {
			// 生成订单号
			String wayBillNo = generateWbNo();

			TTrade trade = new TTrade(null, userId, wayBillNo, TradeSource.WEIXIN, TradeStatus.WAITTING,
					TradeType.GOODS, "众筹夺宝", JSON.toJSONString(orderRo), "众筹夺宝", null, total.toString(), null, null);
			if (orderRo.getTotalCost() == 0) {
				for (InnerGoods innerGood : innerGoods) {
					Integer bidId = innerGood.getBidId();
					// goodsBidMapper.addJoinAmount(innerGoods.getAmount(),
					// bidId);
					TGoodsBid goodsBid = bidMap.get(bidId);
					StringBuilder bidNums = new StringBuilder("");
					for (int i = 1; i <= innerGood.getAmount(); i++) {
						bidNums.append(100000000 + i + goodsBid.getJoinAmount()).append(",");
					}
					TShoppingLog shoppingLog = new TShoppingLog(null, trade.getUserId(), bidId, innerGood.getAmount(),
							goodsBid.getGoodsId(), bidNums.toString(), orderRo.getIp(),
							HttpUtils.getCityByIp(orderRo.getIp()), false, null, null);
					shoppingLogMapper.insertSelective(shoppingLog);
					Integer totalAmount = innerGood.getAmount() + goodsBid.getJoinAmount();
					goodsBid.setJoinAmount(totalAmount);
					if (totalAmount == goodsBid.getTotalAmount()) {
						// 放入待揭晓
						goodsBid.setBidStatus(BidStatus.UNPUBLISHED);
						goodsBid.setUpdateTime(new Date());
						goodsBid.setPublishTime(DateUtils.addMinutes(new Date(), 1));
						TGoods goods = goodsMapper.selectByPrimaryKey(goodsBid.getGoodsId());
						TGoodsBid bid = new TGoodsBid(null, goods.getGoodsId(), goods.getTotalAmount(), 0,
								BidStatus.RUNNING, null, null, null, null, null, goods.getSinglePrice());
						goodsBidMapper.insertSelective(bid);
					}
					goodsBidMapper.updateByPrimaryKeySelective(goodsBid);
					shoppingCartMapper.deleteByUserIdAndGoodsId(trade.getUserId(), goodsBid.getGoodsId());
				}
				trade.setTradeStatus(TradeStatus.SUCCESS);
				this.insertSelective(trade);
				userMapper.updateCoin(trade.getUserId(), new BigDecimal(-orderRo.getCoinPay()));
				return null;
			} else {
				this.insertSelective(trade);
				userMapper.updateCoin(trade.getUserId(), new BigDecimal(-orderRo.getCoinPay()));
				return WePayUtil.getAppPayRequest(orderRo, bidMap, wayBillNo);
			}
		} catch (Exception e) {
			for (InnerGoods innerGood : innerGoods) {
				RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + innerGood.getBidId(), -innerGood.getAmount());
			}
			throw e;
		}
	}

	
	@Override
	public Map<String,String> aliPurchase(OrderRO orderRo, Integer userId) throws IllegalArgumentException, TradeException {
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
		// total += orderRo.getCoinPay();
		Assert.isTrue(orderRo.getTotalCost() + orderRo.getCoinPay() == total.intValue(), "订单总金额不正确，请重新计算");
		if (orderRo.getCoinPay() > 0) {
//			Integer lockedCoid = RedisHelper.incr(String.format(RedisConstant.LOCK_COIN_PRE, userId), 0);
			TUser user = userMapper.selectByPrimaryKey(userId);
			Assert.isTrue(orderRo.getCoinPay() <= user.getCoin().intValue(), "抢币不足！");
		}
		// 1.将所有商品欲购买数量都先加上，方便出错时回滚
		Map<Integer, Integer> bidPurchaseNum = new HashMap<Integer, Integer>();
		for (InnerGoods innerGood : innerGoods) {
			Integer bidId = innerGood.getBidId();
			bidPurchaseNum.put(innerGood.getBidId(),
					RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + bidId, innerGood.getAmount()));
		}
		//
		if (orderRo.getCoinPay() > 0 && orderRo.getTotalCost() == 0) {
			RedisHelper.incr(String.format(RedisConstant.LOCK_COIN_PRE, userId), orderRo.getCoinPay());
		}
		// 2.校验数量
		try {
			for (Integer bidId : bidMap.keySet()) {
				TGoodsBid goodsBid = bidMap.get(bidId);
				Assert.isTrue(bidPurchaseNum.get(bidId) <= goodsBid.getTotalAmount(), "本期商品余量不足");
			}
		} catch (IllegalArgumentException e) {
			rollbackRedis(orderRo, userId);
			throw e;
		}
		try {
			// 生成订单号
			String wayBillNo = generateWbNo();

			TTrade trade = new TTrade(null, userId, wayBillNo, TradeSource.ALIPAY, TradeStatus.WAITTING,
					TradeType.GOODS, "众筹夺宝", JSON.toJSONString(orderRo), "众筹夺宝", null, total.toString(), null, null);
			if (orderRo.getTotalCost() == 0) {
				for (InnerGoods innerGood : innerGoods) {
					Integer bidId = innerGood.getBidId();
					// goodsBidMapper.addJoinAmount(innerGoods.getAmount(),
					// bidId);
					TGoodsBid goodsBid = bidMap.get(bidId);
					StringBuilder bidNums = new StringBuilder("");
					for (int i = 1; i <= innerGood.getAmount(); i++) {
						bidNums.append(100000000 + i + goodsBid.getJoinAmount()).append(",");
					}
					TShoppingLog shoppingLog = new TShoppingLog(null, trade.getUserId(), bidId, innerGood.getAmount(),
							goodsBid.getGoodsId(), bidNums.toString(), orderRo.getIp(),
							HttpUtils.getCityByIp(orderRo.getIp()), false, null, null);
					shoppingLogMapper.insertSelective(shoppingLog);
					Integer totalAmount = innerGood.getAmount() + goodsBid.getJoinAmount();
					goodsBid.setJoinAmount(totalAmount);
					if (totalAmount == goodsBid.getTotalAmount()) {
						// 放入待揭晓
						goodsBid.setBidStatus(BidStatus.UNPUBLISHED);
						goodsBid.setUpdateTime(new Date());
						goodsBid.setPublishTime(DateUtils.addMinutes(new Date(), 1));
						TGoods goods = goodsMapper.selectByPrimaryKey(goodsBid.getGoodsId());
						TGoodsBid bid = new TGoodsBid(null, goods.getGoodsId(), goods.getTotalAmount(), 0,
								BidStatus.RUNNING, null, null, null, null, null, goods.getSinglePrice());
						goodsBidMapper.insertSelective(bid);
					}
					goodsBidMapper.updateByPrimaryKeySelective(goodsBid);
					shoppingCartMapper.deleteByUserIdAndGoodsId(trade.getUserId(), goodsBid.getGoodsId());
				}
				trade.setTradeStatus(TradeStatus.SUCCESS);
				this.insertSelective(trade);
				userMapper.updateCoin(trade.getUserId(), new BigDecimal(-orderRo.getCoinPay()));
				return null;
			} else {
				this.insertSelective(trade);
				userMapper.updateCoin(trade.getUserId(), new BigDecimal(-orderRo.getCoinPay()));
				Map<String,String> result = new HashMap<String,String>();
				result.put("payStr", AliPayService.generatePathParams(trade));
				result.put("tradeNo", trade.getTradeNo());
				return result;
			}
		} catch (Exception e) {
			for (InnerGoods innerGood : innerGoods) {
				RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + innerGood.getBidId(), -innerGood.getAmount());
			}
			throw e;
		}
	}
	/**
	 * 生出订单号
	 * 
	 * @return
	 */
	private String generateWbNo() {
		Integer wbNoCount = RedisHelper.incr(RedisConstant.TRADE_NO_SUF + DateFormatUtils.dateFormat(new Date()));
		if (wbNoCount > 999999 || wbNoCount < 100001) {
			wbNoCount = 100001;
			RedisHelper.set(RedisConstant.TRADE_NO_SUF + DateFormatUtils.dateFormat(new Date()), wbNoCount);
		}
		return DateFormatUtils.dateFormat(new Date()) + wbNoCount;
	}

	private void rollbackRedis(OrderRO orderRo, Integer userId) {
		List<InnerGoods> innerGoods = orderRo.getGoodsList();
		for (InnerGoods innerGood : innerGoods) {
			RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + innerGood.getBidId(), -innerGood.getAmount());
		}
		if (orderRo.getCoinPay() > 0 && orderRo.getTotalCost() == 0){
			RedisHelper.incr(String.format(RedisConstant.LOCK_COIN_PRE, userId), -orderRo.getCoinPay());
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
		Assert.notNull(trade, "不存在的交易" + tradeNo);
		if (TradeStatus.WAITTING != trade.getTradeStatus()) {
			return;
		}
		if ("FAIL".equals(notification.getReturn_code()) || "FAIL".equals(notification.getResult_code())) {
			LOGGER.warn("支付失败" + notification.getOut_trade_no());
			TTrade tradeUpdate = new TTrade();
			tradeUpdate.setTradeId(trade.getTradeId());
			tradeUpdate.setTradeStatus(TradeStatus.FAILURE);
			tradeMapper.updateByPrimaryKeySelective(tradeUpdate);
			if (trade.getTradeType() == TradeType.GOODS) {
				OrderRO orderRo = JSON.parseObject(trade.getTradeInfo(), OrderRO.class);
				List<InnerGoods> innerGoodsList = orderRo.getGoodsList();
				for (InnerGoods innerGoods : innerGoodsList) {
					RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + innerGoods.getBidId(),
							-innerGoods.getAmount());
				}
				if (orderRo.getCoinPay() > 0) {
					RedisHelper.incr(String.format(RedisConstant.LOCK_COIN_PRE, trade.getUserId()),
							-orderRo.getCoinPay());
					userMapper.updateCoin(trade.getUserId(), new BigDecimal(orderRo.getCoinPay()));
				}
			}
			return;
		}
		TTrade tradeUpdate = new TTrade();
		tradeUpdate.setTradeId(trade.getTradeId());
		tradeUpdate.setTradeStatus(TradeStatus.SUCCESS);
		tradeMapper.updateByPrimaryKeySelective(tradeUpdate);
		if (trade.getTradeType() == TradeType.STAMPS) {
			activity(Integer.valueOf(trade.getAmount()), trade.getUserId());
			userMapper.updateCoin(trade.getUserId(), BigDecimal.valueOf(Long.valueOf(trade.getAmount())));
		} else if (trade.getTradeType() == TradeType.GOODS) {
			OrderRO orderRo = JSON.parseObject(trade.getTradeInfo(), OrderRO.class);
			activity(orderRo.getTotalCost(), trade.getUserId());
			List<InnerGoods> innerGoodsList = orderRo.getGoodsList();
			for (InnerGoods innerGoods : innerGoodsList) {
				Integer bidId = innerGoods.getBidId();
				// goodsBidMapper.addJoinAmount(innerGoods.getAmount(), bidId);
				TGoodsBid goodsBid = goodsBidMapper.selectByPrimaryKey(bidId);
				StringBuilder bidNums = new StringBuilder("");
				for (int i = 1; i <= innerGoods.getAmount(); i++) {
					bidNums.append(100000000 + i + goodsBid.getJoinAmount()).append(",");
				}
				TShoppingLog shoppingLog = new TShoppingLog(null, trade.getUserId(), bidId, innerGoods.getAmount(),
						goodsBid.getGoodsId(), bidNums.toString(), orderRo.getIp(),
						HttpUtils.getCityByIp(orderRo.getIp()), false, null, null);
				shoppingLogMapper.insertSelective(shoppingLog);
				Integer totalAmount = innerGoods.getAmount() + goodsBid.getJoinAmount();
				goodsBid.setJoinAmount(totalAmount);
				if (totalAmount == goodsBid.getTotalAmount()) {
					// 放入待揭晓
					goodsBid.setBidStatus(BidStatus.UNPUBLISHED);
					goodsBid.setPublishTime(DateUtils.addMinutes(new Date(), 1));
					TGoods goods = goodsMapper.selectByPrimaryKey(goodsBid.getGoodsId());
					TGoodsBid bid = new TGoodsBid(null, goods.getGoodsId(), goods.getTotalAmount(), 0,
							BidStatus.RUNNING, null, null, null, null, null, goods.getSinglePrice());
					goodsBidMapper.insertSelective(bid);
				}
				goodsBidMapper.updateByPrimaryKeySelective(goodsBid);
				shoppingCartMapper.deleteByUserIdAndGoodsId(trade.getUserId(), goodsBid.getGoodsId());
			}
			if (orderRo.getCoinPay() > 0) {
				// userMapper.updateCoin(trade.getUserId(), new
				// BigDecimal(-orderRo.getCoinPay()));
				RedisHelper.incr(String.format(RedisConstant.LOCK_COIN_PRE, trade.getUserId()), -orderRo.getCoinPay());
			}
		}
		// TODO
		LOGGER.info("TRADE CALLBACK END {}", tradeNo);
	}

	@Override
	public void alipayCallback(Map<String,String> params) throws TradeException {
		
		if(!AliPayService.signVerified(params)){
			throw new TradeException("校验失败");
		}
		String tradeNo = params.get("out_trade_no");
		TTrade trade = tradeMapper.selectByTradeNo(tradeNo);
		if(trade == null){
			throw new TradeException("交易单号不存在："+tradeNo);
		}
		//Assert.isTrue(Double.valueOf(trade.getAmount()).equals(Double.valueOf(params.get("total_amount"))), "订单金额不正确");
		Assert.isTrue("2088421781289181".endsWith(params.get("seller_id")),"商户不匹配");
		if("TRADE_SUCCESS".equals(params.get("trade_status"))){
			TTrade tradeUpdate = new TTrade();
			tradeUpdate.setTradeId(trade.getTradeId());
			tradeUpdate.setTradeStatus(TradeStatus.SUCCESS);
			tradeMapper.updateByPrimaryKeySelective(tradeUpdate);
		}else{
			TTrade tradeUpdate = new TTrade();
			tradeUpdate.setTradeId(trade.getTradeId());
			tradeUpdate.setTradeStatus(TradeStatus.FAILURE);
			tradeMapper.updateByPrimaryKeySelective(tradeUpdate);
		}
		if (trade.getTradeType() == TradeType.STAMPS) {
			activity(Integer.valueOf(trade.getAmount()), trade.getUserId());
			userMapper.updateCoin(trade.getUserId(), BigDecimal.valueOf(Long.valueOf(trade.getAmount())));
		}else if (trade.getTradeType() == TradeType.GOODS) {
			OrderRO orderRo = JSON.parseObject(trade.getTradeInfo(), OrderRO.class);
			activity(orderRo.getTotalCost(), trade.getUserId());
			List<InnerGoods> innerGoodsList = orderRo.getGoodsList();
			for (InnerGoods innerGoods : innerGoodsList) {
				Integer bidId = innerGoods.getBidId();
				// goodsBidMapper.addJoinAmount(innerGoods.getAmount(), bidId);
				TGoodsBid goodsBid = goodsBidMapper.selectByPrimaryKey(bidId);
				StringBuilder bidNums = new StringBuilder("");
				for (int i = 1; i <= innerGoods.getAmount(); i++) {
					bidNums.append(100000000 + i + goodsBid.getJoinAmount()).append(",");
				}
				TShoppingLog shoppingLog = new TShoppingLog(null, trade.getUserId(), bidId, innerGoods.getAmount(),
						goodsBid.getGoodsId(), bidNums.toString(), orderRo.getIp(),
						HttpUtils.getCityByIp(orderRo.getIp()), false, null, null);
				shoppingLogMapper.insertSelective(shoppingLog);
				Integer totalAmount = innerGoods.getAmount() + goodsBid.getJoinAmount();
				goodsBid.setJoinAmount(totalAmount);
				if (totalAmount == goodsBid.getTotalAmount()) {
					// 放入待揭晓
					goodsBid.setBidStatus(BidStatus.UNPUBLISHED);
					goodsBid.setPublishTime(DateUtils.addMinutes(new Date(), 1));
					TGoods goods = goodsMapper.selectByPrimaryKey(goodsBid.getGoodsId());
					TGoodsBid bid = new TGoodsBid(null, goods.getGoodsId(), goods.getTotalAmount(), 0,
							BidStatus.RUNNING, null, null, null, null, null, goods.getSinglePrice());
					goodsBidMapper.insertSelective(bid);
				}
				goodsBidMapper.updateByPrimaryKeySelective(goodsBid);
				shoppingCartMapper.deleteByUserIdAndGoodsId(trade.getUserId(), goodsBid.getGoodsId());
			}
			if (orderRo.getCoinPay() > 0) {
				RedisHelper.incr(String.format(RedisConstant.LOCK_COIN_PRE, trade.getUserId()), -orderRo.getCoinPay());
			}
		}
		
	}
	private void activity(int amount, Integer userId) {
		Integer customerAmount = RedisHelper.incr(String.format(RedisConstant.USER_CUSTOME_AMOUNT_PRE, userId), amount);
		if (customerAmount >= 10) {
			TUser user = userMapper.selectByPrimaryKey(userMapper.selectByPrimaryKey(userId).getInvitor());
			if(user == null){
				return;
			}
			String priceflag = RedisHelper
					.get(String.format(RedisConstant.INVITE_USER_PRICE_FLAG_PRE, user.getUserId()));
			if (StringUtils.isEmpty(priceflag)) {
				RedisHelper.set(String.format(RedisConstant.INVITE_USER_PRICE_FLAG_PRE, user.getUserId()), "1");
				TMessage message = new TMessage();
				message.setContent("尊敬的信誉夺宝用户,您的邀请注册奖励条件已达成,奖励您1抢币。");
				message.setCreateTime(new Date());
				message.setMessageType(MessageType.ACTIVITY);
				message.setTitle("活动奖励");
				message.setReadFlag(false);
				message.setUserId(user.getUserId());
				messageService.addAndPush(message);
			}
		}
	}

	@Override
	public void cancelTrade(String tradeNo, Integer userId) {
		TTrade trade = tradeMapper.selectByTradeNo(tradeNo);
		if (trade == null || trade.getUserId() != userId) {
			return;
		}
		TTrade tradeUpdate = new TTrade();
		tradeUpdate.setTradeId(trade.getTradeId());
		tradeUpdate.setTradeStatus(TradeStatus.FAILURE);
		tradeMapper.updateByPrimaryKeySelective(tradeUpdate);
		if (trade.getTradeType() == TradeType.GOODS) {
			OrderRO orderRo = JSON.parseObject(trade.getTradeInfo(), OrderRO.class);
			List<InnerGoods> innerGoodsList = orderRo.getGoodsList();
			for (InnerGoods innerGoods : innerGoodsList) {
				RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + innerGoods.getBidId(), -innerGoods.getAmount());
			}
			if (orderRo.getCoinPay() > 0) {
				RedisHelper.incr(String.format(RedisConstant.LOCK_COIN_PRE, trade.getUserId()), -orderRo.getCoinPay());
				userMapper.updateCoin(trade.getUserId(), new BigDecimal(orderRo.getCoinPay()));
			}
		}
	}

	@Override
	public List<TradeDonateVO> selectDonateTrade(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize, true, false);
		return tradeMapper.selectDonateTrade();
	}

	@Override
	public String selectDonateAmount(Integer userId) {
		return tradeMapper.selectDonateAmount(userId);
	}

}
