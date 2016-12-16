package team.wuxie.crowdfunding.vo;

import java.util.Date;

import javax.persistence.Column;

import team.wuxie.crowdfunding.domain.TLuckyShare;
import team.wuxie.crowdfunding.domain.TUser;

/**
 * ClassName:LuckyShareVo <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年12月14日 上午8:51:21
 * @see
 */
public class LuckyShareVo extends VO {

	private static final long serialVersionUID = 1L;

	private Integer shareId;

	private Integer userId;

	private String avatar;

	private String nickname;

	private Integer goodsId;

	private Integer bidId;

	/**
	 * 宝贝名称
	 */
	private String goodsName;

	/**
	 * 幸运号码
	 */
	private String luckyNum;

	/**
	 * 评论
	 */
	private String comment;

	/**
	 * 参与人数
	 */
	private Integer joinAmount;

	/**
	 * 分享图片（多图）
	 */
	private String img;

	/**
	 * 开奖时间
	 */
	private Date publishTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public LuckyShareVo(TLuckyShare luckyShare, TUser user) {
		this.shareId = luckyShare.getShareId();
		this.userId = luckyShare.getUserId();
		this.avatar = user.getAvatar();
		this.nickname = user.getNickname();
		this.bidId = luckyShare.getBidId();
		this.goodsId = luckyShare.getGoodsId();
		this.goodsName = luckyShare.getGoodsName();
		this.luckyNum = luckyShare.getLuckyNum();
		this.comment = luckyShare.getComment();
		this.joinAmount = luckyShare.getJoinAmount();
		this.img = luckyShare.getImg();
		this.publishTime = luckyShare.getPublishTime();
		this.createTime = luckyShare.getCreateTime();
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

	public String getAvatar() {
		return avatar;
	}

	public String getNickname() {
		return nickname;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setNickname(String nickName) {
		this.nickname = nickName;
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
	 * @param goodsName
	 *            宝贝名称
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
	 * @param luckyNum
	 *            幸运号码
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
	 * @param comment
	 *            评论
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
	 * @param joinAmount
	 *            参与人数
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
	 * @param img
	 *            分享图片（多图）
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
	 * @param publishTime
	 *            开奖时间
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
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
