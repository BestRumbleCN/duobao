package team.wuxie.crowdfunding.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;
import team.wuxie.crowdfunding.util.i18n.Resources;
@RestController
@RequestMapping("/finance")
@Api(value = "finance - Controller", description = "财务／下单接口")
public class FinanceController extends BaseRestController {
	
	@LoginSkip
	@ApiOperation("下单接口（测试用不涉及第三方支付DONE）")
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ApiResult getToBePublic(@RequestBody OrderRO order){
		try {
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, null);
		} catch (IllegalArgumentException e){
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, Resources.getMessage(e.getMessage()), null);
		}
	} 
}
