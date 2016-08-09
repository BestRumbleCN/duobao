package team.wuxie.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface TUserMapper extends BaseMapper<TUser> {

    TUser selectByUsername(String username);

    List<TUser> selectAllLike(Map map);

    int updatePassword(@Param("userId") Integer userId, @Param("password") String password, @Param("updateId") Integer updateId);
}