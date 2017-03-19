package team.wuxie.crowdfunding.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import team.wuxie.crowdfunding.exception.ServiceException;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;

/**
 * ClassName:GrobalExceptionHandler <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月27日 下午9:36:39
 * @see 	 
 */
@ControllerAdvice
public class GrobalExceptionHandler {
	 private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		if(e instanceof ServiceException){
			return ApiResult.getFailure(MessageId.GENERAL_FAIL, e.getMessage());
		}
		LOGGER.error("服务器未知异常",e);
        return ApiResult.getFailure(MessageId.GENERAL_FAIL, "服务器异常");
    }
}

