package team.wuxie.crowdfunding.vo;

import java.util.Date;

/**
 * <p>
 * 商品VO
 * </p>
 *
 * @author wushige
 * @date 2016-08-16 11:47
 */
public class GoodsVO extends VO {

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 商品类型ID
     */
    private Integer typeId;

    /**
     * 商品类型名称
     */
    private String typeName;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品状态：0-下架、1-上架
     */
    private Boolean goodsStatus;

    /**
     * 夺宝声明
     */
    private String statement;
    
    /**
     * 总需人数
     */
    private Integer totalAmount;
    
    /**
     * 单次购买价格
     */
    private Integer singlePrice;

    /**
     * 商品图片（多图）
     */
    private String img;
    
    /**
     * 图文详情
     */
    private String imgDetail;

    /**
     * 创建时间
     */
    private Date createTime;

    public GoodsVO() {
    }

    public GoodsVO(Integer goodsId, Integer typeId, String typeName, String goodsName, Boolean goodsStatus, String statement, String img, String imgDetail, Date createTime) {
        this.goodsId = goodsId;
        this.typeId = typeId;
        this.typeName = typeName;
        this.goodsName = goodsName;
        this.goodsStatus = goodsStatus;
        this.statement = statement;
        this.img = img;
        this.imgDetail = imgDetail;
        this.createTime = createTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Boolean getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Boolean goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(Integer singlePrice) {
		this.singlePrice = singlePrice;
	}

	public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgDetail() {
		return imgDetail;
	}

	public void setImgDetail(String imgDetail) {
		this.imgDetail = imgDetail;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
