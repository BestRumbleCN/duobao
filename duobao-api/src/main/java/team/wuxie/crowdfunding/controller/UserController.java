package team.wuxie.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.wuxie.crowdfunding.domain.User;
import team.wuxie.crowdfunding.result.ApiResult;
import team.wuxie.crowdfunding.service.UserService;

/**
 * ClassName:UserController <br/>
 *
 * @author fly
 * @version 1.0
 * @see
 * @since 2016年7月15日 下午8:48:00
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ApiResult userDetail(@PathVariable Long userId) {
        User user = userService.selectById(userId);
        if (user == null) {
            return ApiResult.failure("用户不存在");
        } else {
            return ApiResult.success("获取成功", user);
        }
    }
}

