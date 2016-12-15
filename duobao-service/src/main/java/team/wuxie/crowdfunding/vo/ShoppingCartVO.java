package team.wuxie.crowdfunding.vo;

/**
 * ClassName:ShippingCartVO <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月3日 下午7:54:56
 * @see
 */
public class ShoppingCartVO {
	/**
	 * 购物Id
	 */
	private Integer cartId;
	/**
	 * 商品Id
	 */
	private Integer goodsId;
	/**
	 * 期号
	 */
	private Integer bidId;
	/**
	 * 商品图
	 */
	private String img;
	/**
	 * 总量
	 */
	private Integer totalAmount;
	/**
	 * 已参与数量
	 */
	private Integer joinAmount;
	/**
	 * 当前用户选购数量
	 */
	private Integer chooseAmount;
	/**
	 * 商品名称
	 */
	private String goodsName;
	private Integer channel;
	private Integer typeId;
	private Integer singlePrice;

	public Integer getCartId() {
		return cartId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public Integer getBidId() {
		return bidId;
	}

	public String getImg() {
		return img;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public Integer getJoinAmount() {
		return joinAmount;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public Integer getChannel() {
		return channel;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setJoinAmount(Integer joinAmount) {
		this.joinAmount = joinAmount;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public ShoppingCartVO(Integer cartId, Integer goodsId, Integer bidId, String img, Integer totalAmount,
			Integer joinAmount,Integer chooseAmount, String goodsName, Integer channel, Integer typeId, Integer singlePrice) {
		super();
		this.cartId = cartId;
		this.goodsId = goodsId;
		this.bidId = bidId;
		this.img = img;
		this.totalAmount = totalAmount;
		this.joinAmount = joinAmount;
		this.chooseAmount = chooseAmount;
		this.goodsName = goodsName;
		this.channel = channel;
		this.typeId = typeId;
		this.singlePrice = singlePrice;
	}

	public Integer getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(Integer singlePrice) {
		this.singlePrice = singlePrice;
	}

	public Integer getChooseAmount() {
		return chooseAmount;
	}

	public void setChooseAmount(Integer chooseAmount) {
		this.chooseAmount = chooseAmount;
	}
	
}
