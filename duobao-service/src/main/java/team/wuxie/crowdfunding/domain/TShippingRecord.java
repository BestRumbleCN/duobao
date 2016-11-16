package team.wuxie.crowdfunding.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 购买记录表
 */
@Table(name = "t_shipping_record")
public class TShippingRecord implements Serializable {
    /**
     * ID
     */
    @Id
    private Integer id;

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
     * 期数
     */
    @Column(name = "bid_id")
    private Integer bidId;

    /**
     * 幸运号码
     */
    @Column(name = "lucky_num")
    private Integer luckyNum;

    /**
     * 收货人姓名
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 手机号码
     */
    private String cellphone;

    /**
     * 收货地址
     */
    @Column(name = "shipping_address")
    private String shippingAddress;

    /**
     * 状态
     */
    @Column(name = "shipping_status")
    private Byte shippingStatus;

    /**
     * 中奖时间
     */
    @Column(name = "publish_time")
    private Date publishTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public TShippingRecord(Integer id, Integer userId, Integer goodsId, Integer bidId, Integer luckyNum, String receiverName, String cellphone, String shippingAddress, Byte shippingStatus, Date publishTime, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.bidId = bidId;
        this.luckyNum = luckyNum;
        this.receiverName = receiverName;
        this.cellphone = cellphone;
        this.shippingAddress = shippingAddress;
        this.shippingStatus = shippingStatus;
        this.publishTime = publishTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TShippingRecord() {
        super();
    }

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @param userId 用户ID
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
     * @param goodsId 商品ID
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取期数
     *
     * @return bid_id - 期数
     */
    public Integer getBidId() {
        return bidId;
    }

    /**
     * 设置期数
     *
     * @param bidId 期数
     */
    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    /**
     * 获取幸运号码
     *
     * @return lucky_num - 幸运号码
     */
    public Integer getLuckyNum() {
        return luckyNum;
    }

    /**
     * 设置幸运号码
     *
     * @param luckyNum 幸运号码
     */
    public void setLuckyNum(Integer luckyNum) {
        this.luckyNum = luckyNum;
    }

    /**
     * 获取收货人姓名
     *
     * @return receiver_name - 收货人姓名
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 设置收货人姓名
     *
     * @param receiverName 收货人姓名
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    /**
     * 获取手机号码
     *
     * @return cellphone - 手机号码
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * 设置手机号码
     *
     * @param cellphone 手机号码
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    /**
     * 获取收货地址
     *
     * @return shipping_address - 收货地址
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * 设置收货地址
     *
     * @param shippingAddress 收货地址
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress == null ? null : shippingAddress.trim();
    }

    /**
     * 获取状态
     *
     * @return shipping_status - 状态
     */
    public Byte getShippingStatus() {
        return shippingStatus;
    }

    /**
     * 设置状态
     *
     * @param shippingStatus 状态
     */
    public void setShippingStatus(Byte shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    /**
     * 获取中奖时间
     *
     * @return publish_time - 中奖时间
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置中奖时间
     *
     * @param publishTime 中奖时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("userId", userId)
                .add("goodsId", goodsId)
                .add("bidId", bidId)
                .add("luckyNum", luckyNum)
                .add("receiverName", receiverName)
                .add("cellphone", cellphone)
                .add("shippingAddress", shippingAddress)
                .add("shippingStatus", shippingStatus)
                .add("publishTime", publishTime)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .toString();
    }
}