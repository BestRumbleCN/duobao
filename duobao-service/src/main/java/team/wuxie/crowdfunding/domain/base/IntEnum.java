/*
 * Copyright (c) 2009-2013 上海通路快建网络外包服务有限公司 All rights reserved.
 * @(#) IntEnum.java 2013-05-14 19:59
 */

package team.wuxie.crowdfunding.domain.base;

import java.io.Serializable;

/**
 * <p>
 * 整型属性与枚举对象的联系接口
 * </p>
 *
 * @author wushige
 * @date 2016-08-06 18:06
 */
public interface IntEnum extends Serializable {

    /**
     * 返回当前枚举中的整型值。
     */
    int getValue();
}
