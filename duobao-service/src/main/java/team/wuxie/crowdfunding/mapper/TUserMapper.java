package team.wuxie.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.model.UserQuery;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;
import team.wuxie.crowdfunding.vo.UserVO;
import team.wuxie.crowdfunding.vo.UsersStatisticsVO;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TUserMapper extends BaseMapper<TUser> {

    TUser selectByUsername(String username);

    Integer selectIdByUsername(String username);

    int countByUsername(String username);

    Map<Integer, String> findId2NameMap(Collection<Integer> userIds);

    List<TUser> selectAllByQuery(@Param("query") UserQuery query);

    int updatePassword(@Param("userId") Integer userId, @Param("password") String password);

    int updateAvatar(@Param("userId") Integer userId, @Param("avatar") String avatar);

    int updateUserStatus(@Param("userId") Integer userId, @Param("userStatus") boolean userStatus);

    int updateIntegral(@Param("userId") Integer userId, @Param("amount") Integer amount);

    int updateCoin(@Param("userId") Integer userId, @Param("amount") BigDecimal amount);

    TUser selectBySpreadId(String invitor);

    List<TUser> selectByUserIds(Set<Integer> userIds);

    // UserVO相关
    UserVO selectByUserId(@Param("userId") Integer userId);

    // UsersStatisticsVO相关
    List<UsersStatisticsVO> selectByInterval(@Param("interval") Integer interval);

    /**
     * 根据第三方id查询用户
     *
     * @param thirdType 1微信 2微博 3qq
     * @param thirdId
     * @return
     * @author fly
     */
    TUser selectByThirdId(@Param("thirdType") int thirdType, @Param("thirdId") String thirdId);
}