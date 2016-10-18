package team.wuxie.crowdfunding.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.exception.ApiException;
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
     * 注册
     *
     * @return
     */
    @LoginSkip
    @ApiOperation("注册（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "verifyCode", value = "验证码（目前传8888）", required = true, dataType = "String", paramType = "query")
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
}
