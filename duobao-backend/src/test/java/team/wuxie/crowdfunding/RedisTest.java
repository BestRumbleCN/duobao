package team.wuxie.crowdfunding;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
	private RedisTemplate<String, Object> redisTemplate;
	@Test
	public void test() {
		redisTemplate.opsForValue().set("a", "b");
		Assert.assertEquals("b", redisTemplate.opsForValue().get("a"));
	}
}

