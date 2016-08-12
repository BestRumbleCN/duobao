package team.wuxie.crowdfunding.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_goods")
public class TGoods {
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
     * 商品图片（多图）
     */
    private String img;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建者ID
     */
    @Column(name = "create_id")
    private Integer createId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新者ID
     */
    @Column(name = "update_id")
    private Integer updateId;

    public TGoods(Integer goodsId, Integer typeId, String goodsName, Boolean goodsStatus, String statement, String img, Date createTime, Integer createId, Date updateTime, Integer updateId) {
        this.goodsId = goodsId;
        this.typeId = typeId;
        this.goodsName = goodsName;
        this.goodsStatus = goodsStatus;
        this.statement = statement;
        this.img = img;
        this.createTime = createTime;
        this.createId = createId;
        this.updateTime = updateTime;
        this.updateId = updateId;
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
     * 获取创建者ID
     *
     * @return create_id - 创建者ID
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * 设置创建者ID
     *
     * @param createId 创建者ID
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
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

    /**
     * 获取更新者ID
     *
     * @return update_id - 更新者ID
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * 设置更新者ID
     *
     * @param updateId 更新者ID
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }
}