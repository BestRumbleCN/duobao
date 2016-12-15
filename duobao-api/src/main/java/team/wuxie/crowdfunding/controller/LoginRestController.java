package team.wuxie.crowdfunding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.domain.CodeType;
import team.wuxie.crowdfunding.exception.ApiException;
import team.wuxie.crowdfunding.service.SmsCodeService;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;
import team.wuxie.crowdfunding.util.i18n.Resources;
import team.wuxie.crowdfunding.vo.UserVO;

/**
 * <p>
 * 登录相关RestController
 * </p>
 *
 * @author wushige
 * @date 2016-08-10 12:01
 */
@RestController
@RequestMapping
@Api(value = "Login - Controller", description = "登录相关")
public class LoginRestController extends BaseRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    UserService userService;
    
    @Autowired
    SmsCodeService smsCodeService;

    /**
     * 提醒重新登录
     *
     * @return
     */
    @LoginSkip
    @ApiIgnore
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ApiResult auth() {
        return ApiResult.getExpired(MessageId.AUTH);
    }

    /**
     * 登录
     *
     * @return
     */
    @LoginSkip
    @ApiOperation("登录（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<UserVO> login(String username, String password) throws ApiException {
        try {
            UserVO userVO = userService.doLogin(username, password);
            return ApiResult.getSuccess(MessageId.LOGIN, Resources.getMessage("login.success"), userVO);
        } catch (IllegalArgumentException e) {
            return ApiResult.getFailure(MessageId.LOGIN, Resources.getMessage(e.getMessage()), null);
        }
    }
    
    /**
     * 登录
     *
     * @return
     */
    @LoginSkip
    @ApiOperation("第三方登录（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "登录类型 1微信 2微博 3qq", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "thirdId", value = "第三方ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "avatar", value = "头像", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "nickname", value = "昵称", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/thirdLogin", method = RequestMethod.POST)
    public ApiResult<UserVO> thirdLogin(Integer type,String thirdId,String avatar,String nickname) throws ApiException {
        try {
            UserVO userVO = userService.thirdLogin(type, thirdId, avatar, nickname);
            return ApiResult.getSuccess(MessageId.LOGIN, Resources.getMessage("login.success"), userVO);
        } catch (IllegalArgumentException e) {
            return ApiResult.getFailure(MessageId.LOGIN, Resources.getMessage(e.getMessage()), null);
        }
    }

    /**
     * 获取注册验证码
     *
     * @return
     */
    @LoginSkip
    @ApiOperation("获取注册验证码（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cellphone", value = "手机号", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/registerCode", method = RequestMethod.POST)
    public ApiResult register(String cellphone) throws ApiException {
        try {
        	Assert.isNull(userService.selectByUsername(cellphone), "user.cellphone_already_registered");
        	smsCodeService.sendSmsCode(cellphone, CodeType.REGISTER);
            return ApiResult.getSuccess(MessageId.REGISTER, Resources.getMessage("code.send.success"));
        } catch (IllegalArgumentException e) {
            return ApiResult.getFailure(MessageId.REGISTER, Resources.getMessage(e.getMessage()), null);
        }
    }
    
    /**
     * 注册
     *
     * @return
     */
    @LoginSkip
    @ApiOperation("注册（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "verifyCode", value = "验证码（传入手机收到的验证码或8888）", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult register(String userName, String password, String verifyCode) throws ApiException {
        try {
            userService.doRegister(userName, password, verifyCode);
            return ApiResult.getSuccess(MessageId.REGISTER, Resources.getMessage("register.success"));
        } catch (IllegalArgumentException e) {
            return ApiResult.getFailure(MessageId.REGISTER, Resources.getMessage(e.getMessage()), null);
        }
    }

    /**
     * 退出
     *
     * @return
     */
    @ApiOperation("退出（DONE）")
    @ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ApiResult logout() throws ApiException {
        userService.doLogout(getUserId());
        return ApiResult.getSuccess(MessageId.LOGOUT, Resources.getMessage("logout.success"));
    }
    
    /**
     * 获取修改密码验证码
     *
     * @return
     */
    @LoginSkip
    @ApiOperation("获取修改密码验证码（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cellphone", value = "手机号", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/changePswCode", method = RequestMethod.POST)
    public ApiResult changePsw(String cellphone) throws ApiException {
        try {
        	Assert.notNull(userService.selectByUsername(cellphone), "账号未注册");
        	smsCodeService.sendSmsCode(cellphone, CodeType.FORGOT_PASSWORD);
            return ApiResult.getSuccess(MessageId.REGISTER, Resources.getMessage("code.send.success"));
        } catch (IllegalArgumentException e) {
            return ApiResult.getFailure(MessageId.REGISTER, Resources.getMessage(e.getMessage()), null);
        }
    }
    
    /**
	 * 根据验证码修改用户密码
	 *
	 * @return
	 */
    @LoginSkip
	@ApiOperation("根据短信验证码修改用户密码（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cellphone", value = "手机登录账号", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "新密码", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "verifyCode", value = "验证码", required = true, dataType = "String", paramType = "query"), })
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public ApiResult updatePassword(String cellphone,String password, String verifyCode) throws ApiException {
		try {
			userService.changePassword(cellphone, verifyCode, password);
			return ApiResult.getSuccess(MessageId.UPDATE_PASSWORD);
		} catch (IllegalArgumentException e) {
			return ApiResult.getFailure(MessageId.UPDATE_PASSWORD, Resources.getMessage(e.getMessage()));
		}
	}
    
    /**
     * 获取修改密码验证码
     *
     * @return
     */
    @LoginSkip
    @ApiOperation("获取绑定手机号验证码（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cellphone", value = "手机号", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/bindPhoneCode", method = RequestMethod.POST)
    public ApiResult bindPhoneCode(String cellphone) throws ApiException {
        try {
        	Assert.isNull(userService.selectByUsername(cellphone), "手机号已被注册");
        	smsCodeService.sendSmsCode(cellphone, CodeType.BIND_CELLPHONE);
            return ApiResult.getSuccess(MessageId.REGISTER, Resources.getMessage("code.send.success"));
        } catch (IllegalArgumentException e) {
            return ApiResult.getFailure(MessageId.REGISTER, Resources.getMessage(e.getMessage()), null);
        }
    }
}
