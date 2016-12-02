package team.wuxie.crowdfunding.domain.enums;

import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

/**
 * ClassName:TradeSource <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月14日 下午6:36:48
 * @see
 */
public enum TradeSource implements ValueObject<TradeSource>, IntEnum{
	OTHERS(0,"其他"),ALIPAY(1, "支付宝支付"), WEIXIN(2, "微信支付");
	private short value;

	private String name;

	TradeSource() {

	}

	private TradeSource(int value, String name) {
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
	public boolean sameValueAs(TradeSource other) {
		return this.equals(other);
	}
	
	/**
     * 返回指定状态的 {@link TradeSource} 。
     *
     * @param code 指定状态。
     * @return 返回指定状态的 {@link TradeSource} 。
     */
    public static <T extends Number> TradeSource of(T code, TradeSource defaultType) {
        if (code == null) {
            return defaultType;
        }
        for (TradeSource type : values()) {
            if ((int) type.value == code.intValue()) {
                return type;
            }
        }
        return defaultType;
    }
}
