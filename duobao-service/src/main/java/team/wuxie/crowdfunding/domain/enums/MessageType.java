package team.wuxie.crowdfunding.domain.enums;

import com.google.common.collect.Maps;
import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

import java.util.Map;

/**
 * <p>
 * 消息类型枚举
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 14:02
 */
public enum MessageType implements ValueObject<MessageType>, IntEnum {

    DEFAULT(0, "缺省"),

    SYSTEM(1, "系统"),

    CONTACT(2, "客服"),

    ACTIVITY(3, "活动"),

    REWARD(4, "中奖"),

    SHIP(5, "发货")
    ;

    private short value;
    private String name;

    MessageType() {

    }

    MessageType(int value, String name) {
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
    public boolean sameValueAs(MessageType other) {
        return this.equals(other);
    }

    public static int value(MessageType type) {
        return type.getValue();
    }

    /**
     * 返回指定状态的 {@link MessageType} 。
     *
     * @param code 指定状态。
     * @return 返回指定状态的 {@link MessageType} 。
     */
    public static <T extends Number> MessageType of(T code, MessageType defaultType) {
        if (code == null) {
            return defaultType;
        }
        for (MessageType type : values()) {
            if ((int) type.value == code.intValue()) {
                return type;
            }
        }
        return defaultType;
    }

    public static MessageType of(String name, MessageType defaultType) {
        if (name == null || name.length() == 0) {
            return defaultType;
        }
        for (MessageType type : values()) {
            if (type.name().equals(name) || type.getName().equals(name)) {
                return type;
            }
        }
        return defaultType;
    }

    public static Map<String, String> getTypeMap() {
        Map<String, String> values = Maps.newLinkedHashMap();
        for (MessageType type : values()) {
            values.put(String.valueOf(type.getValue()), type.getName());
        }
        return values;
    }
}
