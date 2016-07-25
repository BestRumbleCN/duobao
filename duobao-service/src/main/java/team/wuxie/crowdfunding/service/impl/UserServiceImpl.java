package team.wuxie.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.User;
import team.wuxie.crowdfunding.mapper.UserMapper;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2016-07-25 16:32
 */
@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<User> selectAllLike(Map<String, String> map) {
        return userMapper.selectAllLike(map);
    }
}
