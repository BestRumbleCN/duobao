package team.wuxie.crowdfunding.config;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.TTrade;
import team.wuxie.crowdfunding.domain.enums.BidStatus;
import team.wuxie.crowdfunding.domain.enums.TradeStatus;
import team.wuxie.crowdfunding.domain.enums.TradeType;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.mapper.TTradeMapper;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.ro.order.OrderRO.InnerGoods;
import team.wuxie.crowdfunding.service.FinanceService;
import team.wuxie.crowdfunding.util.date.DateUtils;
import team.wuxie.crowdfunding.util.redis.RedisConstant;
import team.wuxie.crowdfunding.util.redis.RedisHelper;

@Component
public class CalculateWinnerJob implements ApplicationListener<ApplicationReadyEvent> {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CalculateWinnerJob.class);
	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private TGoodsBidMapper goodsBidMapper;
	
	@Autowired
	private TTradeMapper tradeMapper;
	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		ExecutorService es =Executors.newFixedThreadPool(2);
		es.execute(new Runnable() {
			@Override
			public void run() {
				while(true){
					TGoodsBid goodsBid = null;
					try {
						goodsBid = goodsBidMapper.selectUnPublishedGoodsBid();
						if(goodsBid == null){
							try {
								Thread.sleep(300 * 1000);
								continue;
							} catch (InterruptedException e) {
							}
						}
						long leftSecond = DateUtils.timespaceOfSeconds(new Date(), goodsBid.getPublishTime());
						if(leftSecond > 0 ){
							try {
								Thread.sleep(leftSecond * 1000);
								continue;
							} catch (InterruptedException e) {
							}
						}
						financeService.calculateWinner(goodsBid);
					} catch (Exception e) {
						if(goodsBid != null){
							LOGGER.error("揭奖失败，bids️Id＝"+goodsBid.getBidId(),e);
							goodsBid.setBidStatus(BidStatus.BLOCKING);
							goodsBidMapper.updateByPrimaryKeySelective(goodsBid);
						}else{
							LOGGER.error("",e);
						}
						try {
							Thread.sleep(10000);
							continue;
						} catch (InterruptedException e1) {
						}
					}
				}
			}
		});
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		ses.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					List<TTrade> trades = tradeMapper.seleceOverTimeTrade();
					for(TTrade trade : trades){
						if(trade.getTradeType() == TradeType.STAMPS){
							trade.setTradeStatus(TradeStatus.CANCLE);
							tradeMapper.updateByPrimaryKeySelective(trade);
						}else{
							trade.setTradeStatus(TradeStatus.FAILURE);
							tradeMapper.updateByPrimaryKeySelective(trade);
							if(trade.getTradeType() == TradeType.GOODS){
								OrderRO orderRo = JSON.parseObject(trade.getTradeInfo(), OrderRO.class);
								List<InnerGoods> innerGoodsList = orderRo.getGoodsList();
								for (InnerGoods innerGoods : innerGoodsList) {
									RedisHelper.incr(RedisConstant.TEMP_PURCHASE_NUM_PRE + innerGoods.getBidId(), -innerGoods.getAmount());
								}
							}
						}
					}
				} catch (Exception e) {
					LOGGER.error("定时更新过期交易失败",e);
				}
			}
		}, 0l, 15l, TimeUnit.SECONDS);
		//financeService.calculateWinner(goodsBid);
	}

}
