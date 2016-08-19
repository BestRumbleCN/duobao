package team.wuxie.crowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import team.wuxie.crowdfunding.controller.base.BaseController;

/**
 * <p>
 * 活动相关
 * </p>
 *
 * @author wushige
 * @date 2016-08-19 11:22
 */
@Controller
@RequestMapping("/activities")
public class ActivitiesController extends BaseController {

    /**
     * 加载活动视图
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadActivitiesView() {
        return "activity/activity_list";
    }
}
