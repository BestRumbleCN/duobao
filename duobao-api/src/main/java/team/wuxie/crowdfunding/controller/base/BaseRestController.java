package team.wuxie.crowdfunding.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 基本的RestController
 * </p>
 *
 * @author wushige
 * @date 2016-08-10 12:03
 */
public class BaseRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    /**
     * 用户id
     */
    public static final String USER_ID = "userId";

    @Resource
    public HttpServletRequest request;
    @Resource
    public HttpServletResponse response;
    @Resource
    public HttpSession session;

    /**
     * 从HttpRequest中获取Token,然后将Token转换为UserId
     */
    protected Integer getUserId() {
        return (Integer) request.getAttribute(USER_ID);
    }
    
    public String getIpAddr() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
