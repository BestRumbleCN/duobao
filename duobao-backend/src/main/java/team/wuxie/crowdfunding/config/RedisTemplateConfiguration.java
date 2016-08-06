package team.wuxie.crowdfunding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import team.wuxie.crowdfunding.util.redis.ObjectRedisHelper;
import team.wuxie.crowdfunding.util.redis.ObjectTemplate;

/**
 * ClassName:RedisTemplateConfiguration <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年8月4日 下午8:04:41
 * @see
 */
@Configuration
public class RedisTemplateConfiguration {
	@Bean(name = ObjectRedisHelper.TEMPLATE_NAME)
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		return new ObjectTemplate(factory);
	}
}
