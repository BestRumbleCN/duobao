package team.wuxie.crowdfunding.ro.order;

import java.util.List;

/**
 * 商品购买入参
 */
public class OrderRO {
	private List<InnerGoods> goodsList;
	
	private Integer totalCost;
	
	private String ip;
	
	public List<InnerGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<InnerGoods> goodsList) {
		this.goodsList = goodsList;
	}
	
	public Integer getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 	单条商品购买纪录
	 */
	public static class InnerGoods{
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
