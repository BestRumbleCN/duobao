package team.wuxie.crowdfunding.util.jpush;

/**
 * ClassName:JpushType <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月23日 下午5:27:17
 * @see
 */
public enum JpushType {

	NEW_INTERCEPT_PACKAGE(1, "有新的拦截件"),
	NEW_INTERCEPT_WAYBILLNO(2, "有新的面单拦截"),;

	private int value;

	private String name;

	JpushType() {
	}

	private JpushType(int value, String name) {
		this.value = (short) value;
		this.name = name;
	}

	public int getValue() {
		return value;
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

}
