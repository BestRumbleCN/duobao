package team.wuxie.crowdfunding.domain.enums;

import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

/**
 * ClassName:TradeType <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月14日 下午6:45:37
 * @see
 */
public enum TradeType implements ValueObject<TradeType>, IntEnum {
	STAMPS(1, "点券充值"), GOODS(2, "商品购买");
	private short value;

	private String name;

	TradeType() {

	}

	private TradeType(int value, String name) {
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
	public boolean sameValueAs(TradeType other) {
		return this.equals(other);
	}

	/**
	 * 返回指定状态的 {@link TradeType} 。
	 *
	 * @param code
	 *            指定状态。
	 * @return 返回指定状态的 {@link TradeType} 。
	 */
	public static <T extends Number> TradeType of(T code, TradeType defaultType) {
		if (code == null) {
			return defaultType;
		}
		for (TradeType type : values()) {
			if ((int) type.value == code.intValue()) {
				return type;
			}
		}
		return defaultType;
	}
}
