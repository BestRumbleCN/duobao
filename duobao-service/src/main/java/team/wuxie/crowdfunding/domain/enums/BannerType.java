package team.wuxie.crowdfunding.domain.enums;

import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

/**
 * ClassName:BannerType <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年12月14日 下午6:45:37
 * @see
 */
public enum BannerType implements ValueObject<BannerType>, IntEnum {
	URL(1, "URL地址"), GOODS(2, "商品");
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

	/**
	 * 返回指定状态的 {@link BannerType} 。
	 *
	 * @param code
	 *            指定状态。
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
}
