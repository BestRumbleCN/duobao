package team.wuxie.crowdfunding.domain.enums;

import com.google.common.collect.Maps;
import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

import java.util.Map;

/**
 * ClassName:BannerType <br/>
 *
 * @author fly
 * @version 1.0
 * @see
 * @since 2016年12月14日 下午6:45:37
 */
public enum BannerType implements ValueObject<BannerType>, IntEnum {
    DEFAULT(0, "缺省"),
    URL(1, "URL地址"),
    GOODS(2, "商品")
    ;

    private short value;

    private String name;

    BannerType() {

    }

    private BannerType(int value, String name) {
        this.value = (short) value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean sameValueAs(BannerType other) {
        return this.equals(other);
    }

    public static String name(BannerType status) {
        return status.getName();
    }

    /**
     * 返回指定状态的 {@link BannerType} 。
     *
     * @param code 指定状态。
     * @return 返回指定状态的 {@link BannerType} 。
     */
    public static <T extends Number> BannerType of(T code, BannerType defaultType) {
        if (code == null) {
            return defaultType;
        }
        for (BannerType type : values()) {
            if ((int) type.value == code.intValue()) {
                return type;
            }
        }
        return defaultType;
    }

    public static Map<String, String> asMap() {
        Map<String, String> values = Maps.newLinkedHashMap();
        for (BannerType type : values()) {
            values.put(type.toString(), type.getName());
        }
        return values;
    }
}
