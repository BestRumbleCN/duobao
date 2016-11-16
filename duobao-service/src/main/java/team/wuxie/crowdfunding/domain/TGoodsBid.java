package team.wuxie.crowdfunding.domain;

import team.wuxie.crowdfunding.util.mybatis.typehandler.BidStatusTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品竞购表
 */
@Table(name = "t_goods_bid")
public class TGoodsBid {
    /**
     * 商品竞购ID(期数)
     */
    @Id
    @Column(name = "bid_id")
    private Integer bidId;

    /**
     * 商品ID
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 总参与人次（竞购总价格）
     */
    @Column(name = "total_amount")
    private Integer totalAmount;
    
    @Column(name = "single_price")
    private Integer singlePrice;

    /**
     * 已参与人次（剩余人次=总参与人次 - 已参与人次）
     */
    @Column(name = "join_amount")
    private Integer joinAmount;

    /**
     * 竞购状态：0-未上架、1-进行中、2-中断、3-待揭晓、4-已揭晓
     */
    @Column(name = "bid_status")
    @ColumnType(typeHandler = BidStatusTypeHandler.class)
    private BidStatus bidStatus;

    /**
     * 获取用户ID
     */
    @Column(name = "winner_id")
    private Integer winnerId;

    /**
     * 幸运号码
     */
    @Column(name = "lucky_num")
    private Integer luckyNum;

    /**
     * 揭晓时间
     */
    @Column(name = "publish_time")
    private Date publishTime;

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

    public TGoodsBid(Integer bidId, Integer goodsId, Integer totalAmount, Integer joinAmount, BidStatus bidStatus, Integer winnerId, Integer luckyNum, Date publishTime, Date createTime, Date updateTime,Integer singlePrice) {
        this.bidId = bidId;
        this.goodsId = goodsId;
        this.totalAmount = totalAmount;
        this.joinAmount = joinAmount;
        this.bidStatus = bidStatus;
        this.winnerId = winnerId;
        this.luckyNum = luckyNum;
        this.publishTime = publishTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.singlePrice = singlePrice;
    }

    public TGoodsBid() {
        super();
    }

    /**
     * 获取商品竞购ID(期数)
     *
     * @return bid_id - 商品竞购ID(期数)
     */
    public Integer getBidId() {
        return bidId;
    }

    /**
     * 设置商品竞购ID(期数)
     *
     * @param bidId 商品竞购ID(期数)
     */
    public void setBidId(Integer bidId) {
        this.bidId = bidId;
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
     * 获取总参与人次（竞购总价格）
     *
     * @return total_amount - 总参与人次（竞购总价格）
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置总参与人次（竞购总价格）
     *
     * @param totalAmount 总参与人次（竞购总价格）
     */
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
     * 获取已参与人次（剩余人次=总参与人次 - 已参与人次）
     *
     * @return join_amount - 已参与人次（剩余人次=总参与人次 - 已参与人次）
     */
    public Integer getJoinAmount() {
        return joinAmount;
    }

    /**
     * 设置已参与人次（剩余人次=总参与人次 - 已参与人次）
     *
     * @param joinAmount 已参与人次（剩余人次=总参与人次 - 已参与人次）
     */
    public void setJoinAmount(Integer joinAmount) {
        this.joinAmount = joinAmount;
    }

    /**
     * 获取竞购状态：0-未上架、1-进行中、2-中断、3-待揭晓、4-已揭晓
     *
     * @return bid_status - 竞购状态：0-未上架、1-进行中、2-中断、3-待揭晓、4-已揭晓
     */
    public BidStatus getBidStatus() {
        return bidStatus;
    }

    /**
     * 设置竞购状态：0-未上架、1-进行中、2-中断、3-待揭晓、4-已揭晓
     *
     * @param bidStatus 竞购状态：0-未上架、1-进行中、2-中断、3-待揭晓、4-已揭晓
     */
    public void setBidStatus(BidStatus bidStatus) {
        this.bidStatus = bidStatus;
    }

    /**
     * 获取获取用户ID
     *
     * @return winner_id - 获取用户ID
     */
    public Integer getWinnerId() {
        return winnerId;
    }

    /**
     * 设置获取用户ID
     *
     * @param winnerId 获取用户ID
     */
    public void setWinnerId(Integer winnerId) {
        this.winnerId = winnerId;
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
     * 获取揭晓时间
     *
     * @return publish_time - 揭晓时间
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置揭晓时间
     *
     * @param publishTime 揭晓时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
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