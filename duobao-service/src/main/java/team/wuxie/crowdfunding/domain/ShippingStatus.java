package team.wuxie.crowdfunding.domain;

import com.google.common.collect.Maps;
import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

import java.util.Map;

/**
 * 发货状态 <br/>
 */
public enum ShippingStatus implements ValueObject<ShippingStatus>, IntEnum {
	TO_SHIP(0, "待发货"),
    SHIPPED(1, "已发货"),
    SIGNED(2, "已签收"),
    ;

	private short value;
	private String name;

	ShippingStatus() {

	}

	ShippingStatus(int value, String name) {
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

    public static int value(ShippingStatus status) {
        return status.getValue();
    }

	 /**
     * 返回指定状态的 {@link ShippingStatus} 。
     *
     * @param code 指定状态。
     * @return 返回指定状态的 {@link ShippingStatus} 。
     */
    public static <T extends Number> ShippingStatus of(T code, ShippingStatus defaultStatus) {
        if (code == null) {
            return defaultStatus;
        }
        for (ShippingStatus status : values()) {
            if ((int) status.value == code.intValue()) {
                return status;
            }
        }
        return defaultStatus;
    }

    public static ShippingStatus of(String name, ShippingStatus defaultStatus) {
        if (name == null || name.length() == 0) {
            return defaultStatus;
        }
        for (ShippingStatus status : values()) {
            if (status.name().equals(name) || status.getName().equals(name)) {
                return status;
            }
        }
        return defaultStatus;
    }

    public static Map<String, String> asMap() {
        Map<String, String> values = Maps.newLinkedHashMap();
        for (ShippingStatus status : values()) {
            values.put(String.valueOf(status.getValue()), status.getName());
        }
        return values;
    }

	@Override
	public boolean sameValueAs(ShippingStatus other) {
		 return this.equals(other);
	}

}
