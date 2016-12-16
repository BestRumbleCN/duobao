package team.wuxie.crowdfunding.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.service.AreaService;
import team.wuxie.crowdfunding.service.BannerService;
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
	
	@Autowired
	BannerService bannerService;

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
	
	@LoginSkip
	@ApiOperation("首页公告（TEST）")
	@RequestMapping(value = "/announcement", method = RequestMethod.GET)
	public ApiResult announcement() {
		Map<String,String> node = new HashMap<String,String>();
		node.put("title", "信誉夺宝，活动大礼包！点击获取详情");
		node.put("url", "https://www.hao123.com");
		Map<String,String> node2 = new HashMap<String,String>();
		node2.put("title", "信誉夺宝，官方夺宝攻略！点击获取详情");
		node2.put("url", "https://www.hao123.com");
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		result.add(node);
		result.add(node2);
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, result);
	}
	
	@LoginSkip
	@ApiOperation("获取首页轮播图（DONE）")
	@RequestMapping(value = "/banners", method = RequestMethod.GET)
	public ApiResult getBanners() {
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, bannerService.selectOnBanners());
	}
	
	
	
}
