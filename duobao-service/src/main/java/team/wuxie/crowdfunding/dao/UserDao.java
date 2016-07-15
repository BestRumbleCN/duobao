package team.wuxie.crowdfunding.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.wuxie.crowdfunding.domain.UserTest;

/**
 * ClassName:UserDao <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年6月29日 下午7:05:53
 * @see
 */
@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;

	public UserTest selectUserById(int id){
		return this.sqlSession.selectOne("selectUserById", id);
	}
}
