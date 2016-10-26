package team.wuxie.crowdfunding.ro.order;

import java.util.List;

/**
 * 商品购买入参
 */
public class OrderRO {
	private List<InnerGoods> goodsList;
	
	public List<InnerGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<InnerGoods> goodsList) {
		this.goodsList = goodsList;
	}
	
	/**
	 * 	单条商品购买纪录
	 */
	public class InnerGoods{
		private Integer bidId;
		private Integer amount;
		public Integer getAmount() {
			return amount;
		}
		public void setAmount(Integer amount) {
			this.amount = amount;
		}
		public Integer getBidId() {
			return bidId;
		}
		public void setBidId(Integer bidId) {
			this.bidId = bidId;
		}
		
	}

}
