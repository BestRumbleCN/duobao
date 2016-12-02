package team.wuxie.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.domain.enums.PocketStatus;
import team.wuxie.crowdfunding.service.RedPocketService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;

/**
 * ClassName:RedPocketRestController <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月23日 上午10:28:28
 * @see
 */
@RestController
@RequestMapping("/redPocket")
@Api(value = "redPocket - Controller", description = "红包接口")
public class RedPocketRestController extends BaseRestController {

	@Autowired
	private RedPocketService redPocketService;

	@ApiOperation("获取红包（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "status", value = "红包状态（-1-过期、0-可使用、1-已使用）", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/shoppingCard", method = RequestMethod.GET)
	public ApiResult removeCard(Integer status) {
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS,
				redPocketService.selectByUserIdAndStatus(getUserId(), PocketStatus.of(status, PocketStatus.UNUSED)));
	}
}
