package team.wuxie.crowdfunding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.service.AreaService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;

/**
 * ClassName:CommonRestController <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年10月19日 上午8:55:24
 * @see
 */
@RestController
@RequestMapping("/common")
@Api(value = "common - Controller", description = "通用接口")
public class CommonRestController extends BaseRestController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	AreaService areaService;

	@LoginSkip
	@ApiOperation("获取省份列表（DONE）")
	@RequestMapping(value = "/provinces", method = RequestMethod.GET)
	public ApiResult getProvince() {
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, areaService.findProvinces());
	}
	
	@LoginSkip
	@ApiOperation("获取市/县列表（DONE）")
	@ApiImplicitParam(name = "parentId", value = "市县父ID", required = true, dataType = "Integer", paramType = "query")
	@RequestMapping(value = "/subAreas", method = RequestMethod.GET)
	public ApiResult getSubAreas(Long parentId) {
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, areaService.findByParentId(parentId));
	}
}
