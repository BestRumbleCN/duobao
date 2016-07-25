package team.wuxie.crowdfunding.mapper;

import team.wuxie.crowdfunding.domain.User;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

    List<User> selectAllLike(Map map);
}