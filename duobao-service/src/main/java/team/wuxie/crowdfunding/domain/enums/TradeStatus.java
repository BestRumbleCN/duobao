package team.wuxie.crowdfunding.domain.enums;

import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

/**
 * ClassName:TradeStatus <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月14日 下午6:40:33
 * @see
 */
public enum TradeStatus implements ValueObject<TradeStatus>, IntEnum{
	// 0待支付 1支付成功
	FAILURE(-2, "支付失败"), CANCLE(-1, "支付取消"), WAITTING(0, "等待支付"), SUCCESS(1, "支付成功");
	private short value;

	private String name;

	TradeStatus() {

	}

	private TradeStatus(int value, String name) {
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
	public boolean sameValueAs(TradeStatus other) {
		return this.equals(other);
	}
	
	/**
     * 返回指定状态的 {@link TradeStatus} 。
     *
     * @param code 指定状态。
     * @return 返回指定状态的 {@link TradeStatus} 。
     */
    public static <T extends Number> TradeStatus of(T code, TradeStatus defaultType) {
        if (code == null) {
            return defaultType;
        }
        for (TradeStatus type : values()) {
            if ((int) type.value == code.intValue()) {
                return type;
            }
        }
        return defaultType;
    }
}
