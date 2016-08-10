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
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.util.api.ApiResult;

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
@Api(value = "User", description = "当前用户相关")
public class UserRestController extends BaseRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    UserService userService;

    /**
     * 获取用户详情
     *
     * @return
     */
    @ApiOperation("获取用户详情")
    @ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ApiResult getProfile() {
        //todo
        return null;
    }

    /**
     * 更新用户密码
     *
     * @return
     */
    @ApiOperation("更新用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ApiResult updatePassword(String oldPassword, String newPassword) {
        //todo
        return null;
    }

    /**
     * 更新用户详情
     *
     * @return
     */
    @ApiOperation("更新用户详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickname", value = "昵称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "cellphone", value = "手机号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "qq", value = "QQ号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "shippingAddress", value = "收货地址", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ApiResult updateProfile(TUser user) {
        //todo
        return null;
    }

}

