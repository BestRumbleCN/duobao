package team.wuxie.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team.wuxie.crowdfunding.domain.UserTest;
import team.wuxie.crowdfunding.result.ApiResult;
import team.wuxie.crowdfunding.service.UserService;

/**
 * ClassName:UserController <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年7月15日 下午8:48:00
 * @see 	 
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ApiResult userDetail(@PathVariable int id){
		UserTest user = userService.selectUserById(id);
		if(user == null){
			return ApiResult.failure("用户不存在");
		}else{
			return ApiResult.success("获取成功", user);
		}
	}
}

