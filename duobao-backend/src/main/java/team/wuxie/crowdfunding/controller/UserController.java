package team.wuxie.crowdfunding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import team.wuxie.crowdfunding.domain.UserTest;
import team.wuxie.crowdfunding.vo.DataTableModel;

/**
 * ClassName:UserController <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年7月13日 下午5:16:23
 * @see 	 
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value = "/list")
	public DataTableModel<UserTest> findListData(
			@RequestParam("draw") Integer draw,
			@RequestParam("start") Integer start,
			@RequestParam("length") Integer length) {
		DataTableModel<UserTest> result = new DataTableModel<UserTest>(generateUsers(start, length));
		result.setRecordsTotal(100);
		result.setRecordsFiltered(100);
		result.setDraw(draw++);
		return result;
	}
	
	private List<UserTest> generateUsers(int start,int length){
		List<UserTest> result = new ArrayList<UserTest>();
		for(int i = 0;i < length; i++){
			UserTest user = new UserTest();
			user.setUserName("小明"+i);
			user.setId(start + i);
			user.setPoints(10l+ i);
			user.setRemark1("remark1" + start + i);
			user.setRemark2("remark2" + start + i);
			user.setRemark3("remark3" + start + i);
			user.setStatus(i/2);
			result.add(user);
		}
		return result;
	}
}

