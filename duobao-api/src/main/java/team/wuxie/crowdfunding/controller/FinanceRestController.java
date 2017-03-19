package team.wuxie.crowdfunding.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.annotation.RedisLock;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.exception.TradeException;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.service.FinanceService;
import team.wuxie.crowdfunding.service.TradeService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;
import team.wuxie.crowdfunding.util.i18n.Resources;
import team.wuxie.crowdfunding.util.redis.RedisHelper;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.WePayConfig;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.WePayUtil;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.OrderQueryResp;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.PaymentNotification;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.WechatAppPayRequest;

@RestController
@RequestMapping("/finance")
@Api(value = "finance - Controller", description = "财务／下单接口")
public class FinanceRestController extends BaseRestController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	private FinanceService financeService;
	@Autowired
	private TradeService tradeService;

	@RedisLock
	@ApiOperation("微信下单（DONE）")
	@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ApiResult getToBePublic(@RequestBody(required = false) OrderRO order) {
		LOGGER.info("请求数据：" + getRequestBody());
		try {
			// order.setIp("116.228.73.38");
			order.setIp(getIpAddr());
			WechatAppPayRequest result = tradeService.weixinPurchase(order, getUserId());
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "购买成功", result);
		} catch (IllegalArgumentException | TradeException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
	}
	
	@RedisLock
	@ApiOperation("苹果页面下单（DONE）")
	@RequestMapping(value = "/appleTest", method = RequestMethod.POST)
	public ApiResult appleTestOrder(@RequestBody(required = false) OrderRO order) {
		LOGGER.info("请求数据：" + getRequestBody());
		try {
			// order.setIp("116.228.73.38");
			order.setIp(getIpAddr());
			WechatAppPayRequest result = tradeService.weixinPurchase(order, 3);
			RedisHelper.set("applePayTest", "1");
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "购买成功", result);
		} catch (IllegalArgumentException | TradeException e) {
			RedisHelper.set("applePayTest", "0");
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
	}
	
	@LoginSkip
	@RequestMapping(value = "/testResult", method = RequestMethod.GET)
	public ApiResult applePaySuccess(){
		String result = RedisHelper.get("applePayTest");
		RedisHelper.set("applePayTest", "0");
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "", result);
	}
	
	@LoginSkip
	@RequestMapping(value = "/appleTestOrder", method = RequestMethod.POST)
	public ApiResult getTest(@RequestBody(required = false) OrderRO order) {
		try {
			order.setIp(getIpAddr());
			Integer userId = getUserId();
			//WechatAppPayRequest result = tradeService.purchase(order, userId);
			String url = "http://api.tbpmcx.cn:8067/buy.html?params=" +Base64Utils.encodeToString(JSON.toJSONString(order).getBytes());
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "购买", url);
		} catch (IllegalArgumentException | TradeException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
	}

	@ApiOperation("希望树捐赠（微信）（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "amount", value = "金额", required = true, dataType = "int", paramType = "query") })
	@RequestMapping(value = "/donate", method = RequestMethod.POST)
	public ApiResult donate(Integer amount) {
		LOGGER.info("请求数据：" + getRequestBody());
		try {
			// order.setIp("116.228.73.38");
			// order.setIp(getIpAddr());
			WechatAppPayRequest result = tradeService.donate(amount, getUserId(), getIpAddr());
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "购买成功", result);
		} catch (IllegalArgumentException | TradeException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
	}

	@ApiOperation("微信充值接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "amount", value = "金额", required = true, dataType = "int", paramType = "query") })
	@RequestMapping(value = "/weixin/recharge", method = RequestMethod.POST)
	public ApiResult recharge(Integer amount) {
		try {
			WechatAppPayRequest result = tradeService.weixinRecharge(amount, getUserId(), getIpAddr());
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "购买成功", result);
		} catch (IllegalArgumentException | TradeException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
	}
	
	@ApiOperation("支付宝充值接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "amount", value = "金额", required = true, dataType = "int", paramType = "query") })
	@RequestMapping(value = "/alipay/recharge", method = RequestMethod.POST)
	public ApiResult alipayRecharge(Integer amount) {
		try {
			String result = tradeService.alipayRecharge(amount, getUserId(), getIpAddr());
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "购买成功", result);
		} catch (IllegalArgumentException | TradeException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
	}
	
	@LoginSkip
	@ApiOperation("支付宝回调接口")
	@RequestMapping(value = "/alipayCallback", method = {RequestMethod.POST,RequestMethod.GET})
	public String alipayCallback(@RequestParam Map<String,String> params){
		try {
			tradeService.alipayCallback(params);
		} catch (TradeException | IllegalArgumentException e) {
			LOGGER.error("支付宝请求参数"+params);
			LOGGER.error("支付宝回调失败！！", e);
			return "failue";
		}
		return "success";
	}

	@LoginSkip
	@ApiOperation("wx回调接口")
	@RequestMapping(value = "/wxcallback", method = RequestMethod.POST)
	public String callback() {
		OrderQueryResp resp = new OrderQueryResp();
		try {
			String request = getRequestBody();
			LOGGER.info("微信回调数据：" + request);
			PaymentNotification pNotification = WePayUtil.getPaymentNotification(request);
			tradeService.weixinPayCallback(pNotification);
		} catch (TradeException e) {
			resp.setReturn_code(WePayConfig.TRADE_FAIL);
			resp.setReturn_msg(e.getMessage());
			LOGGER.error("微信回调失败！！", e);
			return resp.toResultStr();
		} catch (IllegalArgumentException e) {
			LOGGER.error("微信回调失败！！", e);
		}
		resp.setReturn_code(WePayConfig.TRADE_SUCCESS);
		resp.setReturn_msg("");
		return resp.toResultStr();
	}

	@ApiOperation("微信取消订单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "tradeNo", value = "交易单号", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/weixin/cancelTrade", method = RequestMethod.POST)
	public ApiResult cancelRecharge(String tradeNo) {
		tradeService.cancelTrade(tradeNo, getUserId());
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS);
	}

	@CrossOrigin
	@LoginSkip
	@ApiOperation("获取希望树列表（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示个数", required = true, dataType = "int", paramType = "query"), })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ApiResult getList(Integer pageNum, Integer pageSize) {
		try {
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, tradeService.selectDonateTrade(pageNum, pageSize));
		} catch (IllegalArgumentException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, Resources.getMessage(e.getMessage()), null);
		}
	}
}
