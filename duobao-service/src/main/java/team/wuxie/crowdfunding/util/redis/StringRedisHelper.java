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
	private static ObjectTemplate objectTemplate;

	public static ObjectTemplate getTemplate() {
		if (objectTemplate == null) {
			objectTemplate = (ObjectTemplate) ApplicationContextUtil.getBean(ObjectRedisHelper.TEMPLATE_NAME);
		}
		return objectTemplate;
	}
	
	public static String get(String key){
		String t = (String) getTemplate().opsForValue().get(key);
		return t;
	}
	
	public static void set(String key , String value){
		getTemplate().opsForValue().set(key, value);
	}
	
	public static boolean setnx(String key , String value){
		return getTemplate().opsForValue().setIfAbsent(key, value);
	}
}

