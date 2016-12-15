package team.wuxie.crowdfunding.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 购物车表
 */
@Table(name = "t_shopping_cart")
public class TShoppingCart {
	/**
	 * ID
	 */
	@Id
	@Column(name = "cart_id")
	private Integer cartId;

	/**
	 * 用户ID
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 商品ID
	 */
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 * 选购数量
	 */
	@Column(name = "choose_amount")
	private Integer chooseAmount;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	public TShoppingCart(Integer cartId, Integer userId, Integer goodsId, Integer chooseAmount, Date updateTime,
			Date createTime) {
		this.cartId = cartId;
		this.userId = userId;
		this.goodsId = goodsId;
		this.chooseAmount = chooseAmount;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}

	public TShoppingCart() {
		super();
	}

	/**
	 * 获取ID
	 *
	 * @return cart_id - ID
	 */
	public Integer getCartId() {
		return cartId;
	}

	/**
	 * 设置ID
	 *
	 * @param cartId
	 *            ID
	 */
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	/**
	 * 获取用户ID
	 *
	 * @return user_id - 用户ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 *
	 * @param userId
	 *            用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取商品ID
	 *
	 * @return goods_id - 商品ID
	 */
	public Integer getGoodsId() {
		return goodsId;
	}

	/**
	 * 设置商品ID
	 *
	 * @param goodsId
	 *            商品ID
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * 获取更新时间
	 *
	 * @return update_time - 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getChooseAmount() {
		return chooseAmount;
	}

	public void setChooseAmount(Integer chooseAmount) {
		this.chooseAmount = chooseAmount;
	}
}