package team.wuxie.crowdfunding.domain;

import com.google.common.collect.Maps;
import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

import java.util.Map;

/**
 * <p>
 * 积分类型枚举
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:50
 */
public enum IntegralType implements ValueObject<IntegralType>, IntEnum {

    DEFAULT(-99, "缺省"),

    SIGN(0, "签到"),

    NEW_TASK(1, "新手任务")
    ;

    private short value;
    private String name;

    IntegralType() {

    }

    IntegralType(int value, String name) {
        this.value = (short) value;
        this.name = name;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean sameValueAs(IntegralType other) {
        return this.equals(other);
    }

    public static int value(IntegralType type) {
        return type.getValue();
    }

    /**
     * 返回指定状态的 {@link IntegralType} 。
     *
     * @param code 指定状态。
     * @return 返回指定状态的 {@link IntegralType} 。
     */
    public static <T extends Number> IntegralType of(T code, IntegralType defaultType) {
        if (code == null) {
            return defaultType;
        }
        for (IntegralType type : values()) {
            if ((int) type.value == code.intValue()) {
                return type;
            }
        }
        return defaultType;
    }

    public static IntegralType of(String name, IntegralType defaultType) {
        if (name == null || name.length() == 0) {
            return defaultType;
        }
        for (IntegralType type : values()) {
            if (type.name().equals(name) || type.getName().equals(name)) {
                return type;
            }
        }
        return defaultType;
    }

    public static Map<String, String> getTypeMap() {
        Map<String, String> values = Maps.newLinkedHashMap();
        for (IntegralType type : values()) {
            values.put(String.valueOf(type.getValue()), type.getName());
        }
        return values;
    }
}
