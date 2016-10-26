package team.wuxie.crowdfunding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import team.wuxie.crowdfunding.domain.BidStatus;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.ro.order.OrderRO.InnerGoods;

@Service
public class FinanceService {
	
	@Autowired
	TGoodsBidMapper goodsBidMapper;
	
	

	public void purchase(OrderRO orderRo, Integer userId) {
		// 1.判断金额是否对上
		// 2.判断商品是否够量
		List<InnerGoods> innerGoods = orderRo.getGoodsList();
		Assert.notEmpty(innerGoods, "购物车为空！");
		for (InnerGoods innerGood : innerGoods) {
			Integer bidId = innerGood.getBidId();
			TGoodsBid goodsBid = goodsBidMapper.selectByPrimaryKey(bidId);
			Assert.notNull(goodsBid,"不存在的商品");
			Assert.isTrue(goodsBid.getBidStatus().equals(BidStatus.RUNNING),"本期商品已结束或已下架");
			Assert.isTrue(innerGood.getAmount() +goodsBid.getJoinAmount() < goodsBid.getTotalAmount(),"本期商品余量不足");
			//Integer tempCont = 
		}
//		生成并保存shoppinglog纪录
		// 3.修改user表 修改 goodsbid save
		// 4 如果期数商品满了，放入待揭晓队列
		// 5.
		// 6.
	}
}
