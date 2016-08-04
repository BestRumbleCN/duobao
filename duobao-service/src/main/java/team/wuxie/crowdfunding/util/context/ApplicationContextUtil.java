package team.wuxie.crowdfunding.util.context;

import org.springframework.context.ApplicationContext;

/**
 * ClassName:ApplicationContextUtil <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年8月4日 下午7:26:05
 * @see 	 
 */
public class ApplicationContextUtil {
	
	 private static ApplicationContext applicationContext;

	  public static void setApplicationContext(ApplicationContext context) {
	    applicationContext = context;
	  }
	  
	  public static Object getBean(String beanName){
		  return applicationContext.getBean(beanName);
	  }
}

