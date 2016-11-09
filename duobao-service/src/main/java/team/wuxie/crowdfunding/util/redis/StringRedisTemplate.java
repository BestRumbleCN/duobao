package team.wuxie.crowdfunding.util.redis;

import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * ClassName:ObjectTemplate <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年8月2日 下午6:33:13
 * @see 	 
 */
public class StringRedisTemplate extends RedisTemplate<String, String>{
	public StringRedisTemplate() {
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		setKeySerializer(stringSerializer);
		setHashValueSerializer(stringSerializer);
	}

	/**
	 * Constructs a new <code>StringRedisTemplate</code> instance ready to be used.
	 * 
	 * @param connectionFactory connection factory for creating new connections
	 */
	public StringRedisTemplate(RedisConnectionFactory connectionFactory) {
		this();
		setConnectionFactory(connectionFactory);
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        this.setValueSerializer(stringSerializer);
        this.setHashValueSerializer(stringSerializer);
        this.afterPropertiesSet();
	}

	protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
		return new DefaultStringRedisConnection(connection);
	}
}

