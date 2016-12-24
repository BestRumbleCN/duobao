package team.wuxie.crowdfunding.domain.enums;

import com.google.common.collect.Maps;
import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

import java.util.Map;

/**
 * 竞购状态 <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年10月24日 下午2:22:19
 * @see
 */
public enum BidStatus implements ValueObject<BidStatus>, IntEnum {
	WAIT(0, "待上架"),
    RUNNING(1, "进行中"),
    BLOCKING(2, "中断"),
    UNPUBLISHED(3, "待揭晓"),
    PUBLISHED(4, "已揭晓")
    ;

	private short value;
	private String name;

	BidStatus() {

	}

	BidStatus(int value, String name) {
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

    public static String name(BidStatus status) {
        return status.getName();
    }

	 /**
     * 返回指定状态的 {@link BidStatus} 。
     *
     * @param code 指定状态。
     * @return 返回指定状态的 {@link BidStatus} 。
     */
    public static <T extends Number> BidStatus of(T code, BidStatus defaultType) {
        if (code == null) {
            return defaultType;
        }
        for (BidStatus type : values()) {
            if ((int) type.value == code.intValue()) {
                return type;
            }
        }
        return defaultType;
    }

    public static BidStatus of(String name, BidStatus defaultType) {
        if (name == null || name.length() == 0) {
            return defaultType;
        }
        for (BidStatus type : values()) {
            if (type.name().equals(name) || type.getName().equals(name)) {
                return type;
            }
        }
        return defaultType;
    }

    public static Map<String, String> asMap() {
        Map<String, String> values = Maps.newLinkedHashMap();
        for (BidStatus type : values()) {
            values.put(String.valueOf(type.getValue()), type.getName());
        }
        return values;
    }

	@Override
	public boolean sameValueAs(BidStatus other) {
		 return this.equals(other);
	}

}
