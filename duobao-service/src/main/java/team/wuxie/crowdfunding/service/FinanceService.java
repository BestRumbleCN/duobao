package team.wuxie.crowdfunding.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.TShoppingLog;
import team.wuxie.crowdfunding.domain.enums.BidStatus;
import team.wuxie.crowdfunding.domain.enums.TradeSource;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.mapper.TShoppingLogMapper;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.ro.order.OrderRO.InnerGoods;
import team.wuxie.crowdfunding.util.HttpUtils;
import team.wuxie.crowdfunding.util.date.DateUtils;
import team.wuxie.crowdfunding.util.redis.RedisConstant;
import team.wuxie.crowdfunding.util.redis.RedisHelper;

@Service
@Transactional(readOnly = true)
public class FinanceService {

	@Autowired
	private TGoodsBidMapper goodsBidMapper;

	@Autowired
	private TShoppingLogMapper shoppingLogMapper;

	@Transactional
	public void purchase(OrderRO orderRo, Integer userId, String ip) throws IllegalArgumentException {
		// 1.判断金额是否对上
		// 2.判断商品是否够量
		List<InnerGoods> innerGoods = orderRo.getGoodsList();
		Assert.notEmpty(innerGoods, "购物车为空！");
		List<TShoppingLog> tempLogs = new ArrayList<TShoppingLog>();
		for (InnerGoods innerGood : innerGoods) {
			Integer bidId = innerGood.getBidId();
			TGoodsBid goodsBid = goodsBidMapper.selectByPrimaryKey(bidId);
			Assert.notNull(goodsBid, "不存在的商品");
			Assert.isTrue(goodsBid.getBidStatus().equals(BidStatus.RUNNING), "本期商品已结束或已下架");
			Assert.isTrue(innerGood.getAmount() + goodsBid.getJoinAmount() <= goodsBid.getTotalAmount(), "本期商品余量不足");
			StringBuilder bidNums = new StringBuilder("");
			// Integer tempCont =
			// 生成并保存shoppinglog纪录
			for (int i = 1; i <= innerGood.getAmount(); i++) {
				bidNums.append(10000000 + i + goodsBid.getJoinAmount()).append(",");
			}
			TShoppingLog shoppingLog = new TShoppingLog(null, userId, bidId, innerGood.getAmount(),
					goodsBid.getGoodsId(), bidNums.toString(), ip, HttpUtils.getCityByIp(ip), false, null, null);
			shoppingLogMapper.insertSelective(shoppingLog);
			tempLogs.add(shoppingLog);
			goodsBid.setJoinAmount(innerGood.getAmount() + goodsBid.getJoinAmount());
			if (goodsBid.getJoinAmount() == goodsBid.getTotalAmount()) {
				// 放入待揭晓
				goodsBid.setBidStatus(BidStatus.UNPUBLISHED);
				goodsBid.setPublishTime(DateUtils.addDays(2));
			}
			goodsBidMapper.updateByPrimaryKeySelective(goodsBid);
		}
		// 3.修改user表 修改 goodsbid save
		// 4 如果期数商品满了，放入待揭晓队列
		// 5.
		// 6.
	}

	/**
	 * 订单接口
	 * 
	 * @author fly
	 * @param orderRo
	 * @param userId
	 * @param tradeSource
	 * @throws IllegalArgumentException
	 * @since
	 */
	private void trade(OrderRO orderRo, Integer userId, TradeSource tradeSource) throws IllegalArgumentException {
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
		// 2.
		try {
			for (Integer bidId : bidMap.keySet()) {
				TGoodsBid goodsBid = bidMap.get(bidId);
				Assert.isTrue(bidPurchaseNum.get(bidId) > goodsBid.getTotalAmount(), "本期商品余量不足");
			}
		} catch (IllegalArgumentException e) {
			// 回滚redis记录购买数
			for (InnerGoods innerGood : innerGoods) {
				Integer bidId = innerGood.getBidId();
				RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + bidId, -innerGood.getAmount());
			}
			throw e;
		}
	}

	// private String to
	private void callback() {
		// JSON.
	}
}
