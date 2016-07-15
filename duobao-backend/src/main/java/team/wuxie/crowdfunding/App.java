package team.wuxie.crowdfunding;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import team.wuxie.crowdfunding.util.Constants;


@SpringBootApplication
@EnableAutoConfiguration
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
	@Configuration
	static class WebMvcConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			super.addInterceptors(registry);
			registry.addInterceptor(new HandlerInterceptor() {

				@Override
				public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
						throws Exception {
					Object user = request.getSession().getAttribute(Constants.ADMIN_USER);
					if (user == null) {
						if (request.getHeader("x-requested-with") != null
								&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
							response.setHeader("sessionstatus", "timeout");
							return false;
						}
						response.sendRedirect(request.getContextPath() + "/login");
						return false;
					}
					return true;
				}

				@Override
				public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						ModelAndView modelAndView) throws Exception {

				}

				@Override
				public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
						Exception ex) throws Exception {

				}
			}).excludePathPatterns("/login", "/", "/toLogin");
		}
	}
}
