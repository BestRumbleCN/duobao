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
@Api(value = "Login", description = "登录相关")
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
        return ApiResult.getExpired(MessageId.AUTH.getCode());
    }

    /**
     * 登录
     *
     * @return
     */
    @LoginSkip
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult login(String username, String password) throws ApiException {
        //todo
        return null;
    }

    /**
     * 退出
     *
     * @return
     */
    @ApiOperation("退出")
    @ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ApiResult logout() throws ApiException {
        //todo
        return null;
    }
}
