package team.wuxie.crowdfunding.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * <p>
 * 自定义配置
 * </p>
 *
 * @author wushige
 * @date 2016-06-28 16:20
 */
@Configuration
public class BackendMessageSourceConfig {

    /**
     * 设置国际化能在freemarker中使用
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("i18n/messages");
        messageSource.setBasenames("i18n/core", "i18n/backend");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
