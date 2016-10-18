package team.wuxie.crowdfunding.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:34
 */
@SuppressWarnings("unused")
@Table(name = "t_goods")
public class TGoods implements Serializable {
    /**
     * 商品ID
     */
    @Id
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品类型ID
     */
    @Column(name = "type_id")
    private Integer typeId;

   
    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品状态：0-下架、1-上架
     */
    @Column(name = "goods_status")
    private Boolean goodsStatus;

    /**
     * 夺宝声明
     */
    private String statement;

    /**
     * 总需人数
     */
    @Column(name = "total_amount")
    private Integer totalAmount;
    
    /**
     * 单次购买价格
     */
    @Column(name = "single_price")
    private Integer singlePrice;
    
    /**
     * 商品图片（多图）
     */
    private String img;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public TGoods(Integer goodsId, Integer typeId, String goodsName, Boolean goodsStatus, String statement, Integer totalAmount, Integer singlePrice, String img, Date createTime, Date updateTime) {
        this.goodsId = goodsId;
        this.typeId = typeId;
        this.goodsName = goodsName;
        this.goodsStatus = goodsStatus;
        this.statement = statement;
        this.totalAmount = totalAmount;
        this.singlePrice = singlePrice;
        this.img = img;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TGoods() {
        super();
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
     * @param goodsId 商品ID
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品类型ID
     *
     * @return type_id - 商品类型ID
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置商品类型ID
     *
     * @param typeId 商品类型ID
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 获取商品状态：0-下架、1-上架
     *
     * @return goods_status - 商品状态：0-下架、1-上架
     */
    public Boolean getGoodsStatus() {
        return goodsStatus;
    }

    /**
     * 设置商品状态：0-下架、1-上架
     *
     * @param goodsStatus 商品状态：0-下架、1-上架
     */
    public void setGoodsStatus(Boolean goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    /**
     * 获取夺宝声明
     *
     * @return statement - 夺宝声明
     */
    public String getStatement() {
        return statement;
    }

    /**
     * 设置夺宝声明
     *
     * @param statement 夺宝声明
     */
    public void setStatement(String statement) {
        this.statement = statement == null ? null : statement.trim();
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

	/**
     * 获取商品图片（多图）
     *
     * @return img - 商品图片（多图）
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置商品图片（多图）
     *
     * @param img 商品图片（多图）
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
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
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}