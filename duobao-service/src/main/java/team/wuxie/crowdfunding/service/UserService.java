package team.wuxie.crowdfunding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.wuxie.crowdfunding.dao.UserDao;
import team.wuxie.crowdfunding.domain.UserTest;

/**
 * ClassName:UserService <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年6月29日 下午7:33:49
 * @see 	 
 */
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	

	public UserTest selectUserById(int id){
		return userDao.selectUserById(id);
	}

}

