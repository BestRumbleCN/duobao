package team.wuxie.crowdfunding.util.redis;

import team.wuxie.crowdfunding.util.context.ApplicationContextUtil;

/**
 * ClassName:StringRedisHelper <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年8月2日 下午7:30:10
 * @see 	 
 */
public class RedisHelper {
	private static StringRedisTemplate redisTemplate;

	public static StringRedisTemplate getTemplate() {
		if (redisTemplate == null) {
			redisTemplate = (StringRedisTemplate) ApplicationContextUtil.getBean(RedisConstant.TEMPLATE_NAME);
		}
		return redisTemplate;
	}
	
	public static String get(String key){
		String t =  getTemplate().opsForValue().get(key);
		return t;
	}
	
	public static void set(String key , Object value){
		getTemplate().opsForValue().set(key, value.toString());
	}
	
	public static boolean setnx(String key , String value){
		return getTemplate().opsForValue().setIfAbsent(key, value);
	}
	
	/**
	 * key 中储存的数字值增1（该操作具有原子性）
	 * @author fly
	 * @param key
	 * @return  增加后对应的值
	 * @since
	 */
	public static Integer incr(String key){
		Long result = getTemplate().opsForValue().increment(key, 1l);
		return result.intValue();
	}
	
	/**
	 * key 中储存的数字值增加指定值（该操作具有原子性）
	 * @author fly
	 * @param key
	 * @param addVal
	 * @return  增加后对应的值
	 * @since
	 */
	public static Integer incr(String key,int addVal){
		Long result = getTemplate().opsForValue().increment(key, addVal);
		return result.intValue();
	}
	
	public static boolean setBit(String key,int offset){
		return getTemplate().opsForValue().setBit(key, offset,true);
	}
	
	public static Long bitCount(String key){
		return getTemplate().getConnectionFactory().getConnection().bitCount(key.getBytes());
	}
}

