package team.wuxie.crowdfunding.service.impl;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import team.wuxie.crowdfunding.domain.User;
import team.wuxie.crowdfunding.mapper.UserMapper;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.util.IdGenerator;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.Date;
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

    @Override
    public boolean insertOrUpdate(User user) throws IllegalArgumentException {
        Assert.notNull(user, "argument.is.empty");
        if (user.getUserId() == null) {
            //add
            Assert.hasLength(user.getRole(), "user.role_cannot_be_null");
            user.setUserId(IdGenerator.getId());
            user.setStatus(1);
            user.setLoginTime(new Date());
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            return insertSelective(user);
        } else {
            //update
            return updateSelective(user);
        }
    }
}
