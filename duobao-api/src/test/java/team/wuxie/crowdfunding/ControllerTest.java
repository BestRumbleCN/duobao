package team.wuxie.crowdfunding;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import team.wuxie.crowdfunding.controller.UserController;
import team.wuxie.crowdfunding.result.ApiResult;

/**
 * ClassName:ControllerTest <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年7月15日 下午8:54:54
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class ControllerTest {

	@Autowired
	private UserController userController;
	@Autowired
	public void test1(){
		ApiResult a = userController.userDetail(1);
		Assert.assertSame(a.getStatus(), 0);
	}
}

