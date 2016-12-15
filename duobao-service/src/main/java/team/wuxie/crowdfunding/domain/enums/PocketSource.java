package team.wuxie.crowdfunding.domain.enums;

import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

/**
 * ClassName:PocketSource <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月22日 下午5:10:12
 * @see
 */
public enum PocketSource implements ValueObject<PocketSource>, IntEnum {

	BACKEND(1, "后台"), ACTIVITY(2, "活动"), REGISTER(3, "注册奖励");

	private short value;

	private String name;

	PocketSource() {
	}

	private PocketSource(int value, String name) {
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
	public boolean sameValueAs(PocketSource other) {
		return this.equals(other);
	}

	/**
	 * 返回指定状态的 {@link PocketSource} 。
	 *
	 * @param code
	 *            指定状态。
	 * @return 返回指定状态的 {@link PocketSource} 。
	 */
	public static <T extends Number> PocketSource of(T code, PocketSource defaultType) {
		if (code == null) {
			return defaultType;
		}
		for (PocketSource type : values()) {
			if ((int) type.value == code.intValue()) {
				return type;
			}
		}
		return defaultType;
	}
}
