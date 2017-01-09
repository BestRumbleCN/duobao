package team.wuxie.crowdfunding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.domain.enums.TradeSource;
import team.wuxie.crowdfunding.exception.TradeException;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.service.FinanceService;
import team.wuxie.crowdfunding.service.TradeService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;
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

	@ApiOperation("微信下单（DONE）")
	@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ApiResult getToBePublic(@RequestBody(required= false) OrderRO order) {
		LOGGER.info("请求数据：" + getRequestBody());
		try {
			//order.setIp("116.228.73.38");
			order.setIp(getIpAddr());
			WechatAppPayRequest result = tradeService.purchase(order, getUserId());
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "购买成功", result);
		} catch (IllegalArgumentException | TradeException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
	}

	@ApiOperation("希望树捐赠（微信）（DONE）")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "amount", value = "金额", required = true, dataType = "int", paramType = "query")})
	@RequestMapping(value = "/donate", method = RequestMethod.POST)
	public ApiResult donate(Integer amount) {
		LOGGER.info("请求数据：" + getRequestBody());
		try {
			//order.setIp("116.228.73.38");
			//order.setIp(getIpAddr());
			WechatAppPayRequest result = tradeService.donate(amount, getUserId(), getIpAddr());
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "购买成功", result);
		} catch (IllegalArgumentException | TradeException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
	}
	@ApiOperation("微信充值接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "amount", value = "金额", required = true, dataType = "int", paramType = "query")})
	@RequestMapping(value = "/weixin/recharge", method = RequestMethod.POST)
	public ApiResult recharge(Integer amount) {
		try {
			WechatAppPayRequest result = tradeService.recharge(amount, getUserId(), getIpAddr());
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "购买成功", result);
		} catch (IllegalArgumentException | TradeException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
	}

	@LoginSkip
	@ApiOperation("wx回调接口")
	@RequestMapping(value = "/wxcallback", method = RequestMethod.POST)
	public String callback() {
		OrderQueryResp resp = new OrderQueryResp();
		try {
			String request = getRequestBody();
			LOGGER.info("微信回调数据："+request);
			PaymentNotification pNotification = WePayUtil.getPaymentNotification(request);
			tradeService.weixinPayCallback(pNotification);
		} catch (TradeException e) {
			resp.setReturn_code(WePayConfig.TRADE_FAIL);
			resp.setReturn_msg(e.getMessage());
			LOGGER.error("微信回调失败！！",e);
			return resp.toResultStr();
		}	catch (IllegalArgumentException e) {
			LOGGER.error("微信回调失败！！",e);
		}
		resp.setReturn_code(WePayConfig.TRADE_SUCCESS);
		resp.setReturn_msg("");
		return resp.toResultStr();
	}
	
	@ApiOperation("微信取消订单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "tradeNo", value = "交易单号", required = true, dataType = "String", paramType = "query")})
	@RequestMapping(value = "/weixin/cancelTrade", method = RequestMethod.POST)
	public ApiResult cancelRecharge(String tradeNo){
		tradeService.cancelTrade(tradeNo, getUserId());
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS);
	}

}
