package team.wuxie.crowdfunding.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.domain.TShippingAddress;
import team.wuxie.crowdfunding.domain.TShippingRecord;
import team.wuxie.crowdfunding.domain.TShoppingLog;
import team.wuxie.crowdfunding.domain.enums.BidStatus;
import team.wuxie.crowdfunding.domain.enums.MessageType;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.mapper.TGoodsMapper;
import team.wuxie.crowdfunding.mapper.TShippingAddressMapper;
import team.wuxie.crowdfunding.mapper.TShippingRecordMapper;
import team.wuxie.crowdfunding.mapper.TShoppingLogMapper;
import team.wuxie.crowdfunding.mapper.TUserMapper;

@Service
public class FinanceService {

	@Autowired
	private TGoodsBidMapper goodsBidMapper;

	@Autowired
	private TShoppingLogMapper shoppingLogMapper;

	@Autowired
	private TGoodsMapper goodsMapper;

	@Autowired
	private TShippingAddressMapper shippingAddressMapper;

	@Autowired
	private TShippingRecordMapper shippingRecordMapper;

	@Autowired
	private MessageService messgeService;
	
	@Autowired
	private TUserMapper userMapper;
	@Transactional
	public void calculateWinner(TGoodsBid goodsBid) {
		// 1.计算获奖单号
		int totalAmount = goodsBid.getTotalAmount();
		Random rnd = new Random();
		Integer luckyNum = 100000001 + rnd.nextInt(totalAmount);
		TShoppingLog shoppingLog = shoppingLogMapper.selectByBidNumAndBIdId(goodsBid.getBidId(), luckyNum + "");

		Date publishTime = new Date();
		// 2.修改goodsBid ／ shoppinglog
		goodsBid.setBidStatus(BidStatus.PUBLISHED);
		goodsBid.setLuckyNum(luckyNum);
		goodsBid.setWinnerId(shoppingLog.getUserId());
		goodsBid.setPublishTime(publishTime);
		goodsBid.setUpdateTime(publishTime);
		goodsBidMapper.updateByPrimaryKeySelective(goodsBid);
		shoppingLog.setSelected(true);
		shoppingLogMapper.updateByPrimaryKeySelective(shoppingLog);

		// 3.生成发货记录
		TShippingRecord shippingRecord = new TShippingRecord();
		shippingRecord.setBidId(goodsBid.getBidId());
		shippingRecord.setGoodsId(goodsBid.getGoodsId());
		shippingRecord.setLuckyNum(luckyNum);
		TGoods goods = goodsMapper.selectByPrimaryKey(goodsBid.getGoodsId());
		shippingRecord.setGoodsName(goods.getGoodsName());
		shippingRecord.setPublishTime(publishTime);
		shippingRecord.setUserId(shoppingLog.getUserId());
		
		shippingRecord.setCellphone(userMapper.selectByPrimaryKey(shoppingLog.getUserId()).getCellphone());

		List<TShippingAddress> addressList = shippingAddressMapper.selectByUserId(shoppingLog.getUserId());
		if (!addressList.isEmpty()) {
			TShippingAddress address = addressList.get(0);
			shippingRecord.setReceiverName(address.getName());
			shippingRecord.setShippingAddress(address.getBaseAddress() + " " + address.getAddress());
		}
		shippingRecordMapper.insertSelective(shippingRecord);

		// 4.发送消息
		TMessage message = new TMessage();
		message.setContent(String.format("恭喜您抽中第%s期商品：%s。", goodsBid.getBidId(), goods.getGoodsName()));
		message.setCreateTime(publishTime);
		message.setMessageType(MessageType.REWARD);
		message.setTitle("中奖啦");
		message.setReadFlag(false);
		message.setUserId(shoppingLog.getUserId());
		messgeService.addAndPush(message);
	}

}
