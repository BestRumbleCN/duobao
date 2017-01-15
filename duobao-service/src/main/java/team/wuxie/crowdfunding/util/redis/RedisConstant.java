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
	 * 每日签到 SIGN_IN_{weekNum}_{userId}
	 */
	public final static String SING_IN_PRE = "SIGN_IN_%s_%s";
	
	/**
	 * 用户抢币被锁数量 LOCK_COIN_{user_id}
	 */
	public final static String LOCK_COIN_PRE = "LOCK_COIN_%s";
	
	/**
	 * 当前注册用户数
	 */
	public final static String REGISTER_USER_COUNT = "register_user_count";
	
	/**
	 * 邀请奖励发放标记
	 */
	public final static String INVITE_USER_PRICE_FLAG_PRE = "invite_price_flag_%s";
	
	/**
	 * 用户邀请人数
	 */
	public final static String REGISTER_INVITE_USER_COUNT_PRE = "invite_count_%s";
	
	/**
	 * 用户消费金额
	 */
	public final static String USER_CUSTOME_AMOUNT_PRE = "user_custome_amount_%s";

}
