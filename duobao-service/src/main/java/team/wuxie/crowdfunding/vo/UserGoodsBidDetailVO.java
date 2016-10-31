package team.wuxie.crowdfunding.vo;
/**
 * ClassName:UserGoodsBidDetailVO <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月27日 下午8:40:33
 * @see 	 
 */
public class UserGoodsBidDetailVO extends GoodsBidDetailVO {
	/**
	 * 当前用户本期数夺宝信息
	 */
	private ShoppingLogVO currUserInfo;
	public ShoppingLogVO getCurrUserInfo() {
		
		return currUserInfo;
	}

	public void setCurrUserInfo(ShoppingLogVO currUserInfo) {
		this.currUserInfo = currUserInfo;
	}
}

