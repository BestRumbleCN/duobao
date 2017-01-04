package team.wuxie.crowdfunding.vo;

import com.alibaba.fastjson.annotation.JSONField;

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
	/**
	 * 当前用户购买记录Id
	 */
	@JSONField(serialize=false)
	private Integer itemId;
	public ShoppingLogVO getCurrUserInfo() {
		
		return currUserInfo;
	}

	public void setCurrUserInfo(ShoppingLogVO currUserInfo) {
		this.currUserInfo = currUserInfo;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
}

