package team.wuxie.crowdfunding.config;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import team.wuxie.crowdfunding.domain.CurrentUser;

/**
 * <p>
 * 加了这个类之后就可以在所有的前端页面里面使用currentUser来访问当前用户的属性
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 17:49
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
        //LOGGER.info("Get Current User");
        return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
    }
}
