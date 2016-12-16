package team.wuxie.crowdfunding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.service.LuckyShareService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;
import team.wuxie.crowdfunding.vo.LuckyShareVo;

@RestController
@Api(value = "luckyshare - Controller", description = "好运分享接口")
public class LuckyShareRestController extends BaseRestController {

	@Autowired
	private LuckyShareService luckyShareService;

	@ApiOperation("好运分享（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "comment", value = "评论", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "img", value = "图片（‘,’分割）", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "bidId", value = "商品期号", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/luckyShare", method = RequestMethod.POST)
	public ApiResult luckyShare(Integer bidId, String comment, String img) {
		try {
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS,
					luckyShareService.addLuckyShare(comment, img, bidId,
							getUserId()));
		} catch (IllegalArgumentException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
	}

	@LoginSkip
	@ApiOperation("查看其他用户好运分享（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示个数", required = true, dataType = "int", paramType = "query"), })
	@RequestMapping(value = "/user/{userId}/luckyShare", method = RequestMethod.GET)
	public ApiResult getLuckyShare(@PathVariable("userId") Integer userId,
			Integer pageNum, Integer pageSize) {
		List<LuckyShareVo> result = luckyShareService.selectByUserId(userId,
				pageNum, pageSize);
		return ApiResult.getSuccess(MessageId.GET_OTHER_PROFILE, result);
	}

	@LoginSkip
	@ApiOperation("根据商品id查询好运分享（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示个数", required = true, dataType = "int", paramType = "query"), })
	@RequestMapping(value = "/goods/{goodsId}/luckyShare", method = RequestMethod.GET)
	public ApiResult getLuckyShareByGoodsId(
			@PathVariable("goodsId") Integer goodsId, Integer pageNum,
			Integer pageSize) {
		List<LuckyShareVo> result = luckyShareService.selectByGoodsId(goodsId,
				pageNum, pageSize);
		return ApiResult.getSuccess(MessageId.GET_OTHER_PROFILE, result);
	}

	@LoginSkip
	@ApiOperation("查询所有好运分享（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示个数", required = true, dataType = "int", paramType = "query"), })
	@RequestMapping(value = "/all/luckyShare", method = RequestMethod.GET)
	public ApiResult getLuckyShare(Integer pageNum, Integer pageSize) {
		List<LuckyShareVo> result = luckyShareService.selectAll(pageNum,
				pageSize);
		return ApiResult.getSuccess(MessageId.GET_OTHER_PROFILE, result);
	}

}
