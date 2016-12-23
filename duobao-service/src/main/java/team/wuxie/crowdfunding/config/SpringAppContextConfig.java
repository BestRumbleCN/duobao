package team.wuxie.crowdfunding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.wuxie.crowdfunding.domain.support.Users;

/**
 * @author WuGang
 * @since 1.0
 */
@Configuration("app-context")
public class SpringAppContextConfig extends SpringBaseConfig {

    @Bean
    public Users users() {
        return Users.getInstance();
    }
}
