package team.wuxie.crowdfunding.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.service.GoodsBidService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;
import team.wuxie.crowdfunding.util.i18n.Resources;

/**
 * ClassName:GoodsController <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月24日 下午7:07:10
 * @see 	 
 */
@RestController
@RequestMapping("/goods")
@Api(value = "goods - Controller", description = "商品接口")
public class GoodsController extends BaseRestController {
	
	@Autowired
	private GoodsBidService goodsBidService;
	
	@LoginSkip
	@ApiOperation("获取商品列表（DONE）")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "类别(1:爆款; 2:新货;-1:总需;-2:进度;0:所有)", required = true, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页显示个数", required = true, dataType = "int", paramType = "query"),
		})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ApiResult getList(Integer type,Integer pageNum ,Integer pageSize) {
		try {
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, goodsBidService.selectByType(type, pageNum, pageSize));
		} catch (IllegalArgumentException e){
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, Resources.getMessage(e.getMessage()), null);
		}
	}
	
	@LoginSkip
	@ApiOperation("待揭晓商品列表（DONE）")
	@RequestMapping(value = "/tobePublicList", method = RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页显示个数", required = true, dataType = "int", paramType = "query"),
		})
	public ApiResult getToBePublic(Integer pageNum ,Integer pageSize){
		try {
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, goodsBidService.selectTobePublished(pageNum, pageSize));
		} catch (IllegalArgumentException e){
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, Resources.getMessage(e.getMessage()), null);
		}
	}
	
	@LoginSkip
	@ApiOperation("商品详情（DONE）")
	@ApiImplicitParam(name = "bidId", value = "商品期数", required = true, dataType = "int", paramType = "query")
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ApiResult getDetail(Integer bidId){
		try {
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, goodsBidService.selectDetailByBidId(bidId));
		} catch (IllegalArgumentException e){
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, Resources.getMessage(e.getMessage()), null);
		}
	}
}

