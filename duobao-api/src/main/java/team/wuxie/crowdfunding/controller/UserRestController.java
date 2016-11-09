package team.wuxie.crowdfunding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.domain.TShippingAddress;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.exception.ApiException;
import team.wuxie.crowdfunding.service.ShippingAddressService;
import team.wuxie.crowdfunding.service.ShoppingCartService;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.service.UserTokenService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;
import team.wuxie.crowdfunding.util.i18n.Resources;
import team.wuxie.crowdfunding.vo.UserVO;

/**
 * <p>
 * 当前登录用户相关的RestController
 * </p>
 *
 * @author wushige
 * @date 2016-08-10 12:01
 */
@RestController
@RequestMapping("/user")
@Api(value = "User - Controller", description = "当前用户相关")
public class UserRestController extends BaseRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    UserService userService;
    @Autowired
    UserTokenService userTokenService;

	@Autowired
	ShippingAddressService shippingAddressService;

	@Autowired
	ShoppingCartService shoppingCartService;

	/**
	 * 获取用户详情
	 *
	 * @return
	 */
	@ApiOperation("获取用户详情（DONE）")
	@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ApiResult<UserVO> getProfile() {
		UserVO userVO = userService.selectByUserId(getUserId());
		return ApiResult.getSuccess(MessageId.GET_PROFILE, userVO);
	}

	/**
	 * 更新用户详情
	 *
	 * @return
	 */
	@ApiOperation("更新用户详情,包括更新昵称、头像、qq（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "nickname", value = "昵称", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "avatar", value = "头像", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "qq", value = "QQ号", required = false, dataType = "String", paramType = "query"),
			// @ApiImplicitParam(name = "shippingAddress", value = "收货地址",
			// required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ApiResult<UserVO> updateProfile(String nickname, String avatar, String qq) throws ApiException {
		TUser user = new TUser(getUserId(), nickname, avatar, qq);
		try {
			userService.insertOrUpdate(user);
			UserVO userVO = userService.selectByUserId(getUserId());
			return ApiResult.getSuccess(MessageId.UPDATE_PROFILE, userVO);
		} catch (IllegalArgumentException e) {
			return ApiResult.getFailure(MessageId.UPDATE_PROFILE, Resources.getMessage(e.getMessage()), null);
		}
	}

	@ApiOperation("添加用户邀请码（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "invitor", value = "邀请码", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/profile/invitor", method = RequestMethod.POST)
	public ApiResult<UserVO> addInvitor(String invitor) {
		try {
			UserVO userVO = userService.addInvitor(getUserId(), invitor);
			return ApiResult.getSuccess(MessageId.ADD_INVITOR, userVO);
		} catch (IllegalArgumentException e) {
			return ApiResult.getFailure(MessageId.ADD_INVITOR, Resources.getMessage(e.getMessage()), null);
		}
	}

	/**
	 * 更新用户密码
	 *
	 * @return
	 */
	@ApiOperation("更新用户密码（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query"), })
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public ApiResult updatePassword(String oldPassword, String newPassword) throws ApiException {
		try {
			userService.updatePassword(getUserId(), oldPassword, newPassword);
			return ApiResult.getSuccess(MessageId.UPDATE_PASSWORD);
		} catch (IllegalArgumentException e) {
			return ApiResult.getFailure(MessageId.UPDATE_PASSWORD, Resources.getMessage(e.getMessage()));
		}
	}

	/**
	 * 新增或修改用户收货地址
	 * 
	 * @author fly
	 * @param address
	 * @return
	 * @since
	 */
	@ApiOperation("新增或修改用户地址（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "收件人姓名", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "cellphone", value = "收件人手机", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "address", value = "详细地址", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "provinceId", value = "省份ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "cityId", value = "市ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "prefectureId", value = "县区ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "streetId", value = "街道ID", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "isDefault", value = "是否默认地址", required = true, dataType = "Boolean", paramType = "query"),
			@ApiImplicitParam(name = "addressId", value = "收件地址ID", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/shippingAddr", method = RequestMethod.POST)
	public ApiResult addShippingAddress(TShippingAddress address) {
		Integer userId = getUserId();
		address.setUserId(userId);
		try {
			shippingAddressService.insertOrUpdate(address, getUserId());
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS);
		} catch (IllegalArgumentException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, Resources.getMessage(e.getMessage()));
		}
	}

	@ApiOperation("删除用户地址（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "addressId", value = "收件地址ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/shippingAddr", method = RequestMethod.DELETE)
	public ApiResult delShippingAddress(Integer addressId) {
		Integer userId = getUserId();
		try {
			shippingAddressService.deleteByAddressId(addressId, userId);
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS);
		} catch (IllegalArgumentException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, Resources.getMessage(e.getMessage()));
		}
	}

	@ApiOperation("获取用户地址（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/shippingAddrs", method = RequestMethod.GET)
	public ApiResult getShippingAddress() {
		Integer userId = getUserId();
		try {
			return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, shippingAddressService.selectByUserId(userId));
		} catch (IllegalArgumentException e) {
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, Resources.getMessage(e.getMessage()));
		}
	}

	@ApiOperation("获取购物车（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/shoppingCard", method = RequestMethod.GET)
	public ApiResult shoppingCard() {
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, shoppingCartService.getCartGoods(getUserId()));
	}
	
	@ApiOperation("获取购物车内商品数量（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/shoppingCard/count", method = RequestMethod.GET)
	public ApiResult countCard() {
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, shoppingCartService.countByUserId(getUserId()));
	}

	@ApiOperation("添加到购物车（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsId", value = "商品Id", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/shoppingCard", method = RequestMethod.POST)
	public ApiResult addCard(Integer goodsId) {
		shoppingCartService.addGoods(getUserId(), goodsId);
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS);
	}
	
	@ApiOperation("从购物车删除（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cartId", value = "购物车Id", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/shoppingCard", method = RequestMethod.DELETE)
	public ApiResult removeCard(Integer cartId) {
		shoppingCartService.deleteById(cartId);
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS);
	}
	// /**
	// * 更新用户头像
	// *
	// * @return
	// */
	// @ApiOperation("更新用户头像（TO DO）")
	// @ApiImplicitParams({
	// @ApiImplicitParam(name = "avatar", value = "头像", required = true,
	// dataType = "String", paramType = "query")
	// })
	// @RequestMapping(value = "/avatar", method = RequestMethod.POST)
	// public ApiResult updateAvatar() {
	// //todo
	// return null;
	// }
}
