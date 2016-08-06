/*
 * Copyright (c) 2009-2013 上海通路快建网络外包服务有限公司 All rights reserved.
 * @(#) ValueObject.java 2013-05-14 19:36
 */

package team.wuxie.crowdfunding.domain.base;

import java.io.Serializable;

/**
 * <p>
 * 实现此接口的类，表示一个值对象类。同样的，值对象类也要求实现序列化接口
 * </p>
 *
 * @author wushige
 * @date 2016-08-06 18:06
 */
public interface ValueObject<T extends Serializable> {

    /**
     * 值对象类对象是否相等，需要比较对象内的属性值，它没有唯一标识。
     *
     * @param other 其他的值对象。
     * @return 如果比较的值对象和当前对象相等，则返回 {@code true}，否则 {@code false}。
     */
    boolean sameValueAs(T other);
}
