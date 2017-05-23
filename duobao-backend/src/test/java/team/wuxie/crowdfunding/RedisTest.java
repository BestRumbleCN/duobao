package team.wuxie.crowdfunding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import team.wuxie.crowdfunding.service.TradeService;

/**
 * ClassName:RedisTest <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年8月4日 下午7:52:23
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class RedisTest {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private TradeService tradeService;
	@Test
	public void test() {
		System.out.println(tradeService.selectTradeStatistics(61, "2017-03-01"));
	}
}

