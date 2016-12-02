package team.wuxie.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;
import team.wuxie.crowdfunding.vo.UserVO;
import team.wuxie.crowdfunding.vo.UsersStatisticsVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TUserMapper extends BaseMapper<TUser> {

	TUser selectByUsername(String username);

	List<TUser> selectAllLike(Map map);

	int updatePassword(@Param("userId") Integer userId, @Param("password") String password);

	int updateAvatar(@Param("userId") Integer userId, @Param("avatar") String avatar);

	int updateUserStatus(@Param("userId") Integer userId, @Param("userStatus") boolean userStatus);

	int updateIntegral(@Param("userId") Integer userId, @Param("amount") Integer amount);

	int updateCoin(@Param("userId") Integer userId, @Param("amount") BigDecimal amount);

	TUser selectBySpreadId(String invitor);

	// UserVO相关
	UserVO selectByUserId(@Param("userId") Integer userId);

	// UsersStatisticsVO相关
	List<UsersStatisticsVO> selectByInterval(@Param("interval") Integer interval);

	/**
	 * 根据第三方id查询用户
	 * 
	 * @author fly
	 * @param thirdType
	 *            1微信 2微博 3qq
	 * @param thirdId
	 * @return
	 * @since
	 */
	TUser selectByThirdId(@Param("thirdType") int thirdType, @Param("thirdId") String thirdId);
}