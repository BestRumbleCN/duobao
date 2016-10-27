package team.wuxie.crowdfunding.vo;
/**
 * ClassName:GoodBidDetailVO <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月27日 下午4:06:36
 * @see 	 
 */
public class GoodsBidDetailVO extends GoodsBidVO {
	/**
	 * 中奖购买记录
	 */
	private ShoppingLogVO lotteryInfo;

	public ShoppingLogVO getLotteryInfo() {
		return lotteryInfo;
	}

	public void setLotteryInfo(ShoppingLogVO lotteryInfo) {
		this.lotteryInfo = lotteryInfo;
	}
}

