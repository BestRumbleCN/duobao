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
public enum CodeType implements ValueObject<CodeType>, IntEnum {

    DEFAULT(-99, "缺省"),

    REGISTER(0, "注册"),

    FORGET_PASSWORD(1, "忘记密码")
    ;

    private short value;
    private String name;

    CodeType() {

    }

    CodeType(int value, String name) {
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
    public boolean sameValueAs(CodeType other) {
        return this.equals(other);
    }

    public static int value(CodeType type) {
        return type.getValue();
    }

    /**
     * 返回指定状态的 {@link CodeType} 。
     *
     * @param code 指定状态。
     * @return 返回指定状态的 {@link CodeType} 。
     */
    public static <T extends Number> CodeType of(T code, CodeType defaultType) {
        if (code == null) {
            return defaultType;
        }
        for (CodeType type : values()) {
            if ((int) type.value == code.intValue()) {
                return type;
            }
        }
        return defaultType;
    }

    public static CodeType of(String name, CodeType defaultType) {
        if (name == null || name.length() == 0) {
            return defaultType;
        }
        for (CodeType type : values()) {
            if (type.name().equals(name) || type.getName().equals(name)) {
                return type;
            }
        }
        return defaultType;
    }

    public static Map<String, String> getTypeMap() {
        Map<String, String> values = Maps.newLinkedHashMap();
        for (CodeType type : values()) {
            values.put(String.valueOf(type.getValue()), type.getName());
        }
        return values;
    }
}
