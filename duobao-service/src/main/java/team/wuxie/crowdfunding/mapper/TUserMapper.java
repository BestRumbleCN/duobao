package team.wuxie.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;
import team.wuxie.crowdfunding.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface TUserMapper extends BaseMapper<TUser> {

    TUser selectByUsername(String username);

    List<TUser> selectAllLike(Map map);

    int updatePassword(@Param("userId") Integer userId, @Param("password") String password, @Param("updateId") Integer updateId);

    int updateAvatar(@Param("userId") Integer userId, @Param("avatar") String avatar, @Param("updateId") Integer updateId);

    int updateUserStatus(@Param("userId") Integer userId, @Param("userStatus") boolean userStatus, @Param("updateId") Integer updateId);



    //UserVO相关
    UserVO selectByUserId(@Param("userId") Integer userId);
}