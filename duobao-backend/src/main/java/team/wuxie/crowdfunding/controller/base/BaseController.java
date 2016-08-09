package team.wuxie.crowdfunding.controller.base;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import sun.plugin.liveconnect.SecurityContextHelper;
import team.wuxie.crowdfunding.domain.CurrentUser;
import team.wuxie.crowdfunding.domain.TUser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Security;

/**
 * <p>
 * 基本的Controller
 * </p>
 *
 * @author wushige
 * @date 2016-07-25 17:03
 */
public class BaseController {

    @Resource
    public HttpServletRequest request;
    @Resource
    public HttpServletResponse response;
    @Resource
    public HttpSession session;

    /**
     * 获取当前登录用户
     * @return
     */
    public CurrentUser getCurrentUser() {
        return (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
