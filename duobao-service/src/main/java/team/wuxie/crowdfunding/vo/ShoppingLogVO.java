package team.wuxie.crowdfunding.vo;

import java.util.Date;

/**
 *  中奖购买记录<br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月27日 下午4:14:35
 * @see 	 
 */
public class ShoppingLogVO extends VO{
	private Integer userId;
	private Integer bidId;
	private String nickname;
	private String avatar;
	private Integer amount;
	private String userIp;
	private String ipAddress;
	private String bidNums;
	private String luckyNum;
	private Date createTime;
	private Date publishTime;
	private Date updateTime;
	
	public ShoppingLogVO() {
	}
	
	public Integer getUserId() {
		return userId;
	}
	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	public String getNickname() {
		return nickname;
	}
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getAmount() {
		return amount;
	}
	public String getUserIp() {
		return userIp;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public String getBidNums() {
		return bidNums;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public void setBidNums(String bidNums) {
		this.bidNums = bidNums;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getLuckyNum() {
		return luckyNum;
	}

	public void setLuckyNum(String luckyNum) {
		this.luckyNum = luckyNum;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	
}
