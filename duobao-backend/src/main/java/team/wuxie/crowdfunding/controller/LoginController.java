package team.wuxie.crowdfunding.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

import team.wuxie.crowdfunding.ro.LoginUser;
import team.wuxie.crowdfunding.util.Constants;
import team.wuxie.crowdfunding.util.i18n.Resources;
import team.wuxie.crowdfunding.vo.ajax.Result;

/**
 * ClassName:LoginController <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年7月13日 下午4:46:14
 * @see
 */
@RestController
public class LoginController {
	@Value("${backend.username}")
	String userName;
	@Value("${backend.password}")
	String password;

	@RequestMapping(value = { "/login", "/" })
	public ModelAndView loginPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object user = session.getAttribute(Constants.ADMIN_USER);
		if (user != null) {
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("login");
	}

	@RequestMapping(value = { "/toLogin" }, method = RequestMethod.POST)
	public Result login(@RequestBody LoginUser loginUser, HttpServletRequest request) {
		if (StringUtils.isNullOrEmpty(loginUser.getUserName()) || StringUtils.isNullOrEmpty(loginUser.getPassword())
				|| !loginUser.getUserName().equals(userName) || !loginUser.getPassword().equals(password)) {
			return Result.failure(Resources.getMessage("user.login.failure"));
		}
		HttpSession session = request.getSession();
		session.setAttribute(Constants.ADMIN_USER, Constants.EMPTY_OBJECT);
		return Result.success(Resources.getMessage("user.login.success"));
	}
}
