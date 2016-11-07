package team.wuxie.crowdfunding.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2016-06-29 19:09
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 定义错误页面ErrorPage,需要定义相应的Controller方法指向400、404和500
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
        };
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//
//        super.addResourceHandlers(registry);
//    }
}
