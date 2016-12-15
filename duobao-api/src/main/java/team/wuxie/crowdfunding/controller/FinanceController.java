package team.wuxie.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.domain.enums.TradeSource;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.service.FinanceService;
import team.wuxie.crowdfunding.service.TradeService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;

@RestController
@RequestMapping("/finance")
@Api(value = "finance - Controller", description = "财务／下单接口")
public class FinanceController extends BaseRestController {

	@Autowired
	FinanceService financeService;

	@Autowired
	TradeService tradeService;

	@ApiOperation("下单接口（测试用不涉及第三方支付DONE）")
	@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ApiResult getToBePublic(@RequestBody OrderRO order) {
		try {
			financeService.purchase(order, getUserId(), getIpAddr());
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "购买成功");
		} catch (IllegalArgumentException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
	}

	@ApiOperation("充值接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "amount", value = "金额", required = true, dataType = "Float", paramType = "query"),
			@ApiImplicitParam(name = "tradeSource", value = "充值方式（1支付宝 2微信）", required = true, dataType = "int", paramType = "query") })
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	public ApiResult recharge(float amount, int tradeSource) {
		String value = tradeService.recharge(amount, getUserId(), TradeSource.of(tradeSource, TradeSource.OTHERS));
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, value);
	}
	
	@LoginSkip
	@ApiOperation("充值接口")
	@ResponseBody
	@RequestMapping(value = "/callback", method = RequestMethod.POST)
	public String callback(){
		return "success";
	}

}
