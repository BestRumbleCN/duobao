package team.wuxie.crowdfunding.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_shopping_log")
public class TShoppingLog {
    /**
     * 条目ID
     */
    @Id
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 商品竞购ID
     */
    @Column(name = "bid_id")
    private Integer bidId;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 商品ID
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 购买号码（以逗号分隔）
     */
    @Column(name = "bid_nums")
    private String bidNums;

    /**
     * 用户ip
     */
    @Column(name = "user_ip")
    private String userIp;

    /**
     * ip所在城市
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * 是否包含幸运号
     */
    private Boolean selected;

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

    public TShoppingLog(Integer itemId, Integer userId, Integer bidId, Integer amount, Integer goodsId, String bidNums, String userIp, String ipAddress, Boolean selected, Date createTime, Date updateTime) {
        this.itemId = itemId;
        this.userId = userId;
        this.bidId = bidId;
        this.amount = amount;
        this.goodsId = goodsId;
        this.bidNums = bidNums;
        this.userIp = userIp;
        this.ipAddress = ipAddress;
        this.selected = selected;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TShoppingLog() {
        super();
    }

    /**
     * 获取条目ID
     *
     * @return item_id - 条目ID
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 设置条目ID
     *
     * @param itemId 条目ID
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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
     * 获取商品竞购ID
     *
     * @return bid_id - 商品竞购ID
     */
    public Integer getBidId() {
        return bidId;
    }

    /**
     * 设置商品竞购ID
     *
     * @param bidId 商品竞购ID
     */
    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    /**
     * 获取数量
     *
     * @return amount - 数量
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置数量
     *
     * @param amount 数量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
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
     * 获取购买号码（以逗号分隔）
     *
     * @return bid_nums - 购买号码（以逗号分隔）
     */
    public String getBidNums() {
        return bidNums;
    }

    /**
     * 设置购买号码（以逗号分隔）
     *
     * @param bidNums 购买号码（以逗号分隔）
     */
    public void setBidNums(String bidNums) {
        this.bidNums = bidNums == null ? null : bidNums.trim();
    }

    /**
     * 获取用户ip
     *
     * @return user_ip - 用户ip
     */
    public String getUserIp() {
        return userIp;
    }

    /**
     * 设置用户ip
     *
     * @param userIp 用户ip
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    /**
     * 获取ip所在城市
     *
     * @return ip_address - ip所在城市
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置ip所在城市
     *
     * @param ipAddress ip所在城市
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    /**
     * 获取是否包含幸运号
     *
     * @return selected - 是否包含幸运号
     */
    public Boolean getSelected() {
        return selected;
    }

    /**
     * 设置是否包含幸运号
     *
     * @param selected 是否包含幸运号
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
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
}