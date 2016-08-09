package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.CurrentUser;
import team.wuxie.crowdfunding.domain.Role;
import team.wuxie.crowdfunding.service.CurrentUserService;

import java.util.Objects;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 17:39
 */
@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Integer userId) {
        LOGGER.debug("Checking if user={} has access to userId={}", currentUser, userId);
        return currentUser != null && (Objects.equals(currentUser.getRole(), Role.ADMIN) || currentUser.getUserId().equals(userId));
    }
}
