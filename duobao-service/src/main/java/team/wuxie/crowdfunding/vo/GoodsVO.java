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
     * 商品图片（多图）
     */
    private String img;

    /**
     * 创建时间
     */
    private Date createTime;

    public GoodsVO() {
    }

    public GoodsVO(Integer goodsId, String typeName, String goodsName, Boolean goodsStatus, String statement, String img, Date createTime) {
        this.goodsId = goodsId;
        this.typeName = typeName;
        this.goodsName = goodsName;
        this.goodsStatus = goodsStatus;
        this.statement = statement;
        this.img = img;
        this.createTime = createTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
