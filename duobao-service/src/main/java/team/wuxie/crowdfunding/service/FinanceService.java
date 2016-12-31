package team.wuxie.crowdfunding.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.TShoppingLog;
import team.wuxie.crowdfunding.domain.enums.BidStatus;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.mapper.TShoppingLogMapper;

@Service
public class FinanceService {

	@Autowired
	private TGoodsBidMapper goodsBidMapper;

	@Autowired
	private TShoppingLogMapper shoppingLogMapper;

	public void calculateWinner(TGoodsBid goodsBid){
		//1.计算获奖单号
		int totalAmount = goodsBid.getTotalAmount();
		 Random rnd = new Random();
		Integer luckyNum = 100000001 + rnd.nextInt(totalAmount);
		TShoppingLog shoppingLog = shoppingLogMapper.selectByBidNumAndBIdId(goodsBid.getBidId(), luckyNum+"");
		
		//2.修改goodsBid ／ shoppinglog
		goodsBid.setBidStatus(BidStatus.PUBLISHED);
		goodsBid.setLuckyNum(luckyNum);
		goodsBid.setWinnerId(shoppingLog.getUserId());
		goodsBidMapper.updateByPrimaryKeySelective(goodsBid);
		shoppingLog.setSelected(true);
		shoppingLogMapper.updateByPrimaryKeySelective(shoppingLog);
	}
	
}
