package team.wuxie.crowdfunding.util.redis;

public final class RedisConstant {

	public final static String TEMPLATE_NAME = "redisTemplate";
	/**
	 * 商品预购买数量key前缀（tem_purchase_num_{bidId}）
	 */
	public final static String TEMP_PURCHASE_NUM_PRE = "tem_purchase_num_";
	
	/**
	 * 支付订单号
	 */
	public final static String TRADE_NO_SUF = "trade_no_pre";
	
	/**
	 * 订单号前缀
	 */
	public final static String WAYBILL_NO_PRE = "WAYBILL_NO_";
}
