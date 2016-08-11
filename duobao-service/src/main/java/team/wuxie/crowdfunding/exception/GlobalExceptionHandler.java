package team.wuxie.crowdfunding.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;
import team.wuxie.crowdfunding.util.i18n.Resources;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 定义Controller层全局默认异常处理
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 11:58
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 定义默认的Web异常处理handler
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = WebException.class)
    public ModelAndView defaultWebErrorHandler(HttpServletRequest request, WebException e) throws Exception {
        e.printStackTrace();
        StringBuffer url = request.getRequestURL();
        LOGGER.error(url.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.addObject("url", url);
        modelAndView.setViewName("common/error");
        return modelAndView;
    }

    /**
     * 定义默认的Ajax异常处理handler
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = AjaxException.class)
    @ResponseBody
    public AjaxResult defaultAjaxErrorHandler(HttpServletRequest request, AjaxException e) throws Exception {
        e.printStackTrace();
        StringBuffer url = request.getRequestURL();
        LOGGER.error(url.toString());
        return AjaxResult.getFailure(e.getMessage());
    }

    /**
     * 定义默认的Api异常处理handler
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public ApiResult defaultApiErrorHandler(HttpServletRequest request, ApiException e) throws Exception {
        e.printStackTrace();
        StringBuffer url = request.getRequestURL();
        LOGGER.error(url.toString());
        return ApiResult.getFailure(MessageId.BAD_REQUEST, Resources.getMessage("bad.request"));
    }
}
