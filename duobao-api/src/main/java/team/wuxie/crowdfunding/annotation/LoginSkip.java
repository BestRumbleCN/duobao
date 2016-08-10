package team.wuxie.crowdfunding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 跳过登录注解
 * </p>
 *
 * @author wushige
 * @date 2016-08-10 12:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginSkip {
}
