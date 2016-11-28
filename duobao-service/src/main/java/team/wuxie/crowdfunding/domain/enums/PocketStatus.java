package team.wuxie.crowdfunding.domain.enums;

import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

/**
 * ClassName:PocketStatus <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月22日 下午5:15:47
 * @see
 */
public enum PocketStatus implements ValueObject<PocketStatus>, IntEnum {

	EXPIRED(-1, "过期"), UNUSED(0, "未使用"), USED(1, "已使用");

	private short value;

	private String name;

	PocketStatus() {
	}

	private PocketStatus(int value, String name) {
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
	public boolean sameValueAs(PocketStatus other) {
		return this.equals(other);
	}

	public static int value(PocketStatus status) {
        return status.getValue();
    }

    public static String name(PocketStatus status) {
        return status.getName();
    }
	/**
	 * 返回指定状态的 {@link PocketStatus} 。
	 *
	 * @param code
	 *            指定状态。
	 * @return 返回指定状态的 {@link PocketStatus} 。
	 */
	public static <T extends Number> PocketStatus of(T code, PocketStatus defaultType) {
		if (code == null) {
			return defaultType;
		}
		for (PocketStatus type : values()) {
			if ((int) type.value == code.intValue()) {
				return type;
			}
		}
		return defaultType;
	}
}
