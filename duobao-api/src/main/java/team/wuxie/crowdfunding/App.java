package team.wuxie.crowdfunding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import team.wuxie.crowdfunding.util.context.ApplicationContextUtil;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
    	ApplicationContext applicationContext = SpringApplication.run(App.class, args);
    	ApplicationContextUtil.setApplicationContext(applicationContext);
    }
}
