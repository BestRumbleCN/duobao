package team.wuxie.crowdfunding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.wuxie.crowdfunding.domain.support.*;

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

    @Bean
    public Goods goods() {
        return Goods.getInstance();
    }

    @Bean
    public Activities activities() {
        return Activities.getInstance();
    }

    @Bean
    public Areas areas() {
        return Areas.getInstance();
    }

    @Bean
    public Shippings shippings() {
        return Shippings.getInstance();
    }

    @Bean
    public Shoppings shoppings() {
        return Shoppings.getInstance();
    }

    @Bean
    public Banners banners() {
        return Banners.getInstance();
    }

    @Bean
    public LuckyShares luckyShares() {
        return LuckyShares.getInstance();
    }

    @Bean
    public Messages messages() {
        return Messages.getInstance();
    }

    @Bean
    public SystemUsers systemUsers() {
        return SystemUsers.getInstance();
    }

    @Bean
    public Trades trades() {
        return Trades.getInstance();
    }


}
