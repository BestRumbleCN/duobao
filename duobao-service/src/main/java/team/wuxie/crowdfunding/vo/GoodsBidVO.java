package team.wuxie.crowdfunding.vo;

import java.util.Date;

import team.wuxie.crowdfunding.domain.enums.BidStatus;

/**
 * ClassName:GoodsBidVO <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月24日 下午3:58:07
 * @see 	 
 */
public class GoodsBidVO extends VO {
		/**
		 * 商品期数
		 */
	 	private Integer bidId;
	    /**
	     * 商品ID
	     */
	    private Integer goodsId;
	    
	    /**
	     * 商品图片（多图）
	     */
	    private String img;
	    
	    /**
	     * 图文详情
	     */
	    private String imgDetail;
	    
	    /**
	     * 商品频道（0，普通 1，爆款 2，新货）
	     */
	    private Integer channel;
	    
	    /**
	     * 商品类型ID
	     */
	    private Integer typeId;

	    /**
	     * 总参与人次（竞购总价格）
	     */
	    private Integer totalAmount;

	    /**
	     * 已参与人次（剩余人次=总参与人次 - 已参与人次）
	     */
	    private Integer joinAmount;

	    /**
	     * 竞购状态：0-未上架、1-进行中、2-中断、3-待揭晓、4-已揭晓
	     */
	    private BidStatus bidStatus;

	    /**
	     * 获取用户ID
	     */
	    private Integer winnerId;

	    /**
	     * 幸运号码
	     */
	    private Integer luckyNum;

	    /**
	     * 揭晓时间
	     */
	    private Date publishTime;
	    
	    /**
	     * 创建时间
	     */
	    private Date createTime;
	    
	    private Integer singlePrice;
	    
	    
	    private String goodsName;
	    
	    /**
	     * 距揭晓剩余秒数
	     */
	    private Long leftSeconds;
	    
	    /**
	     * 获奖者名称
	     */
	    private String winnerName;
	    

		public Integer getBidId() {
			return bidId;
		}

		public Integer getGoodsId() {
			return goodsId;
		}

		public Integer getTotalAmount() {
			return totalAmount;
		}

		public Integer getJoinAmount() {
			return joinAmount;
		}

		public BidStatus getBidStatus() {
			return bidStatus;
		}

		public Integer getWinnerId() {
			return winnerId;
		}

		public Integer getLuckyNum() {
			return luckyNum;
		}

		public Date getPublishTime() {
			return publishTime;
		}

		public String getImg() {
			return img;
		}

		public String getImgDetail() {
			return imgDetail;
		}

		public Integer getChannel() {
			return channel;
		}

		public Integer getTypeId() {
			return typeId;
		}

		public void setBidId(Integer bidId) {
			this.bidId = bidId;
		}

		public void setGoodsId(Integer goodsId) {
			this.goodsId = goodsId;
		}

		public void setTotalAmount(Integer totalAmount) {
			this.totalAmount = totalAmount;
		}

		public void setJoinAmount(Integer joinAmount) {
			this.joinAmount = joinAmount;
		}

		public void setBidStatus(BidStatus bidStatus) {
			this.bidStatus = bidStatus;
		}

		public void setWinnerId(Integer winnerId) {
			this.winnerId = winnerId;
		}

		public void setLuckyNum(Integer luckyNum) {
			this.luckyNum = luckyNum;
		}

		public void setPublishTime(Date publishTime) {
			this.publishTime = publishTime;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public void setImgDetail(String imgDetail) {
			this.imgDetail = imgDetail;
		}

		public void setChannel(Integer channel) {
			this.channel = channel;
		}

		public void setTypeId(Integer typeId) {
			this.typeId = typeId;
		}

		public Integer getSinglePrice() {
			return singlePrice;
		}

		public void setSinglePrice(Integer singlePrice) {
			this.singlePrice = singlePrice;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public Long getLeftSeconds() {
			return leftSeconds;
		}

		public void setLeftSeconds(Long leftSeconds) {
			this.leftSeconds = leftSeconds;
		}

		public String getWinnerName() {
			return winnerName;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public void setWinnerName(String winnerName) {
			this.winnerName = winnerName;
		}

}

