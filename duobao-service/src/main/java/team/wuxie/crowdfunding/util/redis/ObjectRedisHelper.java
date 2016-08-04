package team.wuxie.crowdfunding.util.redis;

import team.wuxie.crowdfunding.util.context.ApplicationContextUtil;

/**
 * ClassName:RedisHelper <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年8月2日 下午6:44:51
 * @see
 */
public class ObjectRedisHelper<T> {

	public final static String TEMPLATE_NAME = "redisTemplate";
	
	private static ObjectTemplate objectTemplate;

	public ObjectTemplate getTemplate() {
		if (objectTemplate == null) {
			objectTemplate = (ObjectTemplate) ApplicationContextUtil.getBean(TEMPLATE_NAME);
		}
		return objectTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public T get(String key){
		T t = (T) getTemplate().opsForValue().get(key);
		return t;
	}
	
	public void set(String key , T value){
		getTemplate().opsForValue().set(key, value);
	}
	
	public boolean setnx(String key , T value){
		return getTemplate().opsForValue().setIfAbsent(key, value);
	}
	
}
