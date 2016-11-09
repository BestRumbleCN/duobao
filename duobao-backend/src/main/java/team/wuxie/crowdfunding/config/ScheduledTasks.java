package team.wuxie.crowdfunding.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import team.wuxie.crowdfunding.mapper.TSmsCodeMapper;

/**
 * ClassName:ScheduledTasks <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月28日 下午4:56:22
 * @see 	 
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
	Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	@Autowired
	private TSmsCodeMapper smsCodeMapper;
	
	@Scheduled(cron = "1 0 0 * * ?")
	public void orgInfoQuartz() {
		logger.info("每日发送短信数量清理 定时任务开始执行");
		smsCodeMapper.cleanDailyTimes();
		logger.info("每日发送短信数量清理 定时任务结束执行");
	}
}

