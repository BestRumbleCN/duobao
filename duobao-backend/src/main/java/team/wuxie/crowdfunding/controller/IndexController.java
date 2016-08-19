package team.wuxie.crowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import team.wuxie.crowdfunding.controller.base.BaseController;

/**
 * <p>
 * 首页相关
 * </p>
 *
 * @author wushige
 * @date 2016-08-18 10:01
 */
@Controller
@RequestMapping
public class IndexController extends BaseController {

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String loadIndexView() {
        return "index/index";
    }

    /**
     * 数据统计
     * @return
     */
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String loadStatisticsView() {
        return "index/statistics";
    }

    /**
     * 系统设置
     * @return
     */
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public String loadSettingView() {
        return "index/setting";
    }

    /**
     * 权限管理
     * @return
     */
    @RequestMapping(value = "/privilege", method = RequestMethod.GET)
    public String loadPrivilegeView() {
        return "index/privilege";
    }
}
