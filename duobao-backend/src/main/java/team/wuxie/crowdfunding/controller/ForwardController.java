package team.wuxie.crowdfunding.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName:ForwardController <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年7月13日 下午5:17:43
 * @see 	 
 */
@RestController
public class ForwardController {
	
	@RequestMapping(value = { "/user/management", "/user" })
	public ModelAndView loginPage(HttpServletRequest request) {
		return new ModelAndView("user/management");
	}
	
	@RequestMapping("/index")
	public ModelAndView toHomePage(){
		return new ModelAndView("index");
	}
}

