package team.wuxie.crowdfunding.config;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.enums.BidStatus;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.service.FinanceService;
import team.wuxie.crowdfunding.util.date.DateUtils;

@Component
public class CalculateWinnerJob implements ApplicationListener<ApplicationReadyEvent> {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CalculateWinnerJob.class);
	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private TGoodsBidMapper goodsBidMapper;
	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		ExecutorService es =Executors.newSingleThreadExecutor();
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
		//financeService.calculateWinner(goodsBid);
	}

}
