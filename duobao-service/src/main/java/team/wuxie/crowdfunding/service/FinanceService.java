package team.wuxie.crowdfunding.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import team.wuxie.crowdfunding.domain.BidStatus;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.TShoppingLog;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.mapper.TShoppingLogMapper;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.ro.order.OrderRO.InnerGoods;

@Service
public class FinanceService {

	@Autowired
	TGoodsBidMapper goodsBidMapper;
	
	@Autowired
	TShoppingLogMapper shoppingLogMapper;

	@Transactional
	public void purchase(OrderRO orderRo, Integer userId) throws IllegalArgumentException{
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
			Assert.isTrue(innerGood.getAmount() + goodsBid.getJoinAmount() < goodsBid.getTotalAmount(), "本期商品余量不足");
			StringBuilder bidNums = new StringBuilder("");
			// Integer tempCont =
			// 生成并保存shoppinglog纪录
			for (int i = 1; i <= innerGood.getAmount(); i++) {
				bidNums.append(10000000 + i + goodsBid.getJoinAmount()).append(",");
			}
			TShoppingLog shoppingLog = new TShoppingLog(null, userId, bidId, innerGood.getAmount(),
					goodsBid.getGoodsId(), bidNums.toString(), "192.168.1.1","上海",false, null, null);
			shoppingLogMapper.insertSelective(shoppingLog);
			tempLogs.add(shoppingLog);
			goodsBid.setJoinAmount(innerGood.getAmount() + goodsBid.getJoinAmount());
			if(goodsBid.getJoinAmount() == goodsBid.getTotalAmount()){
				 //放入待揭晓
				goodsBid.setBidStatus(BidStatus.UNPUBLISHED);
			}
			goodsBidMapper.updateByPrimaryKeySelective(goodsBid);
		}
		// 3.修改user表 修改 goodsbid save
		// 4 如果期数商品满了，放入待揭晓队列
		// 5.
		// 6.
	}
}
