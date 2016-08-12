package team.wuxie.crowdfunding.domain;

import java.util.Date;
import javax.persistence.*;

/**
 * <p>
 * 商品类型表
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:34
 */
@Table(name = "t_goods_type")
public class TGoodsType {
    /**
     * 商品类型ID
     */
    @Id
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 商品类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 商品类型图片
     */
    @Column(name = "type_img")
    private String typeImg;

    /**
     * 商品类型状态：1-上架、0-下架
     */
    private Boolean status;

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

    public TGoodsType(Integer typeId, String typeName, String typeImg, Boolean status, Date createTime, Integer createId, Date updateTime, Integer updateId) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeImg = typeImg;
        this.status = status;
        this.createTime = createTime;
        this.createId = createId;
        this.updateTime = updateTime;
        this.updateId = updateId;
    }

    public TGoodsType() {
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
     * 获取商品类型名称
     *
     * @return type_name - 商品类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置商品类型名称
     *
     * @param typeName 商品类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * 获取商品类型图片
     *
     * @return type_img - 商品类型图片
     */
    public String getTypeImg() {
        return typeImg;
    }

    /**
     * 设置商品类型图片
     *
     * @param typeImg 商品类型图片
     */
    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg == null ? null : typeImg.trim();
    }

    /**
     * 获取商品类型状态：1-上架、0-下架
     *
     * @return status - 商品类型状态：1-上架、0-下架
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置商品类型状态：1-上架、0-下架
     *
     * @param status 商品类型状态：1-上架、0-下架
     */
    public void setStatus(Boolean status) {
        this.status = status;
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