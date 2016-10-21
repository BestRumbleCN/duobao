package team.wuxie.crowdfunding.ro.goods;

import javax.validation.constraints.NotNull;

/**
 * ClassName:goodsRo <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月20日 下午7:52:51
 * @see 	 
 */
public class GoodsRO {
	
	@NotNull(message="goods.typeId_cannot_be_null")
	 private Integer typeId;
	
	@NotNull(message="goods.goodsName_cannot_be_null")
	 private String goodsName;
	
	 private Integer channel;
	 
	 private Boolean goodsStatus;
	 
	 @NotNull(message="goods.totalamount_cannot_be_null")
	 private Integer totalAmount;
	 
	 @NotNull(message="goods.singleprice_cannot_be_null")
	 private Integer singlePrice;
	 
	 @NotNull(message="goods.img_cannot_be_null")
	 private String img;
	 
	 private String imgDetail;

	public Integer getTypeId() {
		return typeId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Boolean getGoodsStatus() {
		return goodsStatus;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public Integer getSinglePrice() {
		return singlePrice;
	}

	public String getImg() {
		return img;
	}

	public String getImgDetail() {
		return imgDetail;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setGoodsStatus(Boolean goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setSinglePrice(Integer singlePrice) {
		this.singlePrice = singlePrice;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setImgDetail(String imgDetail) {
		this.imgDetail = imgDetail;
	}
}

