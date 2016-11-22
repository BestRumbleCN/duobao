package team.wuxie.crowdfunding.util.redis;

import team.wuxie.crowdfunding.util.context.ApplicationContextUtil;

/**
 * ClassName:StringRedisHelper <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年8月2日 下午7:30:10
 * @see 	 
 */
public class StringRedisHelper {
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
	
	public static void set(String key , String value){
		getTemplate().opsForValue().set(key, value);
	}
	
	public static boolean setnx(String key , String value){
		return getTemplate().opsForValue().setIfAbsent(key, value);
	}
}

