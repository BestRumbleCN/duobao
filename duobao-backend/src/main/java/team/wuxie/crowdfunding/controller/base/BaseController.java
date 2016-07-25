package team.wuxie.crowdfunding.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * <p>
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
}
