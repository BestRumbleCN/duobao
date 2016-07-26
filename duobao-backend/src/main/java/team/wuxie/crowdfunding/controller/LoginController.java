package team.wuxie.crowdfunding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.wuxie.crowdfunding.exception.AjaxException;
import team.wuxie.crowdfunding.exception.ApiException;
import team.wuxie.crowdfunding.exception.WebException;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.i18n.Resources;

/**
 * ClassName:LoginController <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年7月13日 下午4:46:14
 * @see
 */
@Controller
@RequestMapping
public class LoginController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @RequestMapping(value = {"/", "login"}, method = RequestMethod.GET)
    public String loadLoginView(String error, Model model) {
        LOGGER.debug("Getting login page, error={}", error);
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String loadIndexView() {
        return "index";
    }

    @RequestMapping(value = "/ajaxExceptionTest", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult testAjaxException() throws AjaxException {
        throw new AjaxException(Resources.getMessage("operation.failure"));
    }

    @RequestMapping(value = "/apiExceptionTest", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult testApiException() throws ApiException {
        throw new ApiException(Resources.getMessage("operation.failure"));
    }

    @RequestMapping(value = "/webExceptionTest", method = RequestMethod.GET)
    public String loadExceptionView() throws WebException {
        throw new WebException(Resources.getMessage("operation.failure"));
    }

    /**
     * 400错误页面
     *
     * @return
     */
    @RequestMapping(value = "/400", method = RequestMethod.GET)
    public String load400View() {
        return "common/400";
    }

    /**
     * 404错误页面
     *
     * @return
     */
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String load404View() {
        return "common/404";
    }

    /**
     * 500错误页面
     *
     * @return
     */
    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String load500View() {
        return "common/500";
    }
}
