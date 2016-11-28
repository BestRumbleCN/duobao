package team.wuxie.crowdfunding.util.goodsbid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 待抽奖倒计时工<br/>
 * @author   fly
 * @version  1.0
 * @since    2016年11月19日 下午2:01:30
 * @see 	 
 */
@Component
public class GoodsBidContext implements CommandLineRunner{

	//@Value("goodsbid.context.init.flag")
	private boolean goodsBidFlag = false;
	
	@Override
	public void run(String... args) throws Exception {
		if(goodsBidFlag){
			
		}
	}

}

