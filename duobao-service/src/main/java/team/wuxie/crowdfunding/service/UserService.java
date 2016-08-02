package team.wuxie.crowdfunding.service;

import team.wuxie.crowdfunding.domain.User;
import team.wuxie.crowdfunding.util.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2016-07-25 16:23
 */
public interface UserService extends BaseService<User> {

    User selectByUsername(String username);

    List<User> selectAllLike(Map<String, String> map);

    boolean insertOrUpdate(User user) throws IllegalArgumentException;
}
