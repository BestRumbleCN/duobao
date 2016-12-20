package team.wuxie.crowdfunding.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 好运分享记录表
 */
@Table(name = "t_lucky_share")
public class TLuckyShare implements Serializable {

    private static final long serialVersionUID = 1065670125232038165L;

    @Id
    @Column(name = "share_id")
    private Integer shareId;

    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "bid_id")
    private Integer bidId;

    /**
     * 宝贝名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 幸运号码
     */
    @Column(name = "lucky_num")
    private String luckyNum;

    /**
     * 评论
     */
    private String comment;

    /**
     * 参与人数
     */
    @Column(name = "join_amount")
    private Integer joinAmount;

    /**
     * 分享图片（多图）
     */
    private String img;

    /**
     * 开奖时间
     */
    @Column(name = "publish_time")
    private Date publishTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public TLuckyShare(Integer shareId, Integer userId, Integer bidId,Integer goodsId, String goodsName, String luckyNum, String comment, Integer joinAmount, String img, Date publishTime, Date createTime) {
        this.shareId = shareId;
        this.userId = userId;
        this.bidId = bidId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.luckyNum = luckyNum;
        this.comment = comment;
        this.joinAmount = joinAmount;
        this.img = img;
        this.publishTime = publishTime;
        this.createTime = createTime;
    }

    public TLuckyShare() {
        super();
    }

    /**
     * @return share_id
     */
    public Integer getShareId() {
        return shareId;
    }

    /**
     * @param shareId
     */
    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/**
     * @return bid_id
     */
    public Integer getBidId() {
        return bidId;
    }

    /**
     * @param bidId
     */
    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    /**
     * 获取宝贝名称
     *
     * @return goods_name - 宝贝名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置宝贝名称
     *
     * @param goodsName 宝贝名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 获取幸运号码
     *
     * @return lucky_num - 幸运号码
     */
    public String getLuckyNum() {
        return luckyNum;
    }

    /**
     * 设置幸运号码
     *
     * @param luckyNum 幸运号码
     */
    public void setLuckyNum(String luckyNum) {
        this.luckyNum = luckyNum == null ? null : luckyNum.trim();
    }

    /**
     * 获取评论
     *
     * @return comment - 评论
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置评论
     *
     * @param comment 评论
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * 获取参与人数
     *
     * @return join_amount - 参与人数
     */
    public Integer getJoinAmount() {
        return joinAmount;
    }

    /**
     * 设置参与人数
     *
     * @param joinAmount 参与人数
     */
    public void setJoinAmount(Integer joinAmount) {
        this.joinAmount = joinAmount;
    }

    /**
     * 获取分享图片（多图）
     *
     * @return img - 分享图片（多图）
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置分享图片（多图）
     *
     * @param img 分享图片（多图）
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * 获取开奖时间
     *
     * @return publish_time - 开奖时间
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置开奖时间
     *
     * @param publishTime 开奖时间
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("shareId", shareId)
                .add("userId", userId)
                .add("goodsId", goodsId)
                .add("bidId", bidId)
                .add("goodsName", goodsName)
                .add("luckyNum", luckyNum)
                .add("comment", comment)
                .add("joinAmount", joinAmount)
                .add("img", img)
                .add("publishTime", publishTime)
                .add("createTime", createTime)
                .toString();
    }
}