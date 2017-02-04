package team.wuxie.crowdfunding;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.domain.TUserToken;
import team.wuxie.crowdfunding.service.UserTokenService;
import team.wuxie.crowdfunding.util.AccessTokenUtil;
import team.wuxie.crowdfunding.util.context.ApplicationContextUtil;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(App.class, args);
        ApplicationContextUtil.setApplicationContext(applicationContext);
    }

    /**
     * 配置登录拦截器
     */
    @Configuration
    static class WebMvcConfig extends WebMvcConfigurerAdapter {

        private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

        @Autowired
        UserTokenService userTokenService;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new HandlerInterceptor() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//                    return true;

                    String reqUrl = request.getRequestURI().replace(request.getContextPath(), "");
                    LOGGER.info(String.format("url:%s", reqUrl));
                    if(reqUrl.contains("registerCode") || reqUrl.contains("checkCode") || reqUrl.contains("register")){
//                    	response.addHeader("Access-Control-Allow-Origin", "*");
//                    	response.setContentType("application/json;charset=UTF-8");
                    	return true;
                    }
                    if(reqUrl.contains("goods/detail") ||reqUrl.contains("/appleTest")){
                    	response.addHeader("Access-Control-Allow-Origin", "*");
                    	response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                    	response.setContentType("application/json");
                    	return true;
                    }
                    if (reqUrl.contains("html") || reqUrl.contains("static") || reqUrl.contains("docs")
                            || reqUrl.contains("resources") || reqUrl.contains("images")|| reqUrl.contains("staticsrc")) return true;

                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    Method method = handlerMethod.getMethod();
                    LoginSkip annotation = method.getAnnotation(LoginSkip.class);
                    if (annotation != null) return true;

                    String accessToken = AccessTokenUtil.getAccessToken(request);
                    if (StringUtils.isNoneBlank(accessToken)) {
                        TUserToken userToken = userTokenService.getByUserToken(accessToken);
                        if (userToken == null) {
                            LOGGER.error(String.format("accessToken无效，accessToken:%s，无法根据accessToken查询出用户", accessToken));
                            response.sendRedirect(request.getContextPath() + "/auth");
                            return false;
                        } else if (userToken.isExpired(TUserToken.SESSION_EXPIRED_TIME) || userToken.getLogoutTime() != null) {
                            LOGGER.error(String.format("accessToken已过期，accessToken:%s，请重新登录", accessToken));
                            response.sendRedirect(request.getContextPath() + "/auth");
                            return false;
                        } else {
                            request.setAttribute(BaseRestController.USER_ID, userToken.getUserId());
                            return true;
                        }
                    } else {
                        LOGGER.error("accessToken不存在，禁止访问。。。");
                        response.sendRedirect(request.getContextPath() + "/auth");
                        return false;
                    }
                }

                @Override
                public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

                }

                @Override
                public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

                }
            });
        }
    }
}