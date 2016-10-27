package team.wuxie.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.service.FinanceService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;
@RestController
@RequestMapping("/finance")
@Api(value = "finance - Controller", description = "财务／下单接口")
public class FinanceController extends BaseRestController {
	
	@Autowired
	FinanceService financeService;
	
	@ApiOperation("下单接口（测试用不涉及第三方支付DONE）")
	@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ApiResult getToBePublic(@RequestBody OrderRO order){
		try {
			financeService.purchase(order, getUserId());
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, "购买成功");
		} catch (IllegalArgumentException e){
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		} 
	} 
}
