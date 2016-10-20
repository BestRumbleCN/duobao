package team.wuxie.crowdfunding.ro.goods;

import org.springframework.beans.BeanUtils;

/**
 * @author fly
 * @version @param <T> RO类
 * @version @param <V> 原始类
 * @since 2016年10月20日 下午8:25:33
 */
public abstract class AbstractConverter<T,V> {
	
	public abstract T getROInstatnce(); 
	
	public abstract V getInstance();
	
	public T converterToRO(V v){
		T t = getROInstatnce();
		BeanUtils.copyProperties(v, t);
		return t;
	}
	
	public V ROconverteTo(T t){
		V v = getInstance();
		BeanUtils.copyProperties(t, v);
		return v;
	}
	
}

