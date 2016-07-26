package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.CurrentUser;
import team.wuxie.crowdfunding.domain.User;
import team.wuxie.crowdfunding.service.UserService;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 17:41
 */
@Service
public class CurrentUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsServiceImpl.class);
    @Autowired
    UserService userService;


    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Authenticating user with username={}", username);
        User user = userService.selectByUsername(username);
        if (user == null)
                throw new UsernameNotFoundException(String.format("user with username=%s was not found", username));
        return new CurrentUser(user);
    }
}
