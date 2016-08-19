package team.wuxie.crowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import team.wuxie.crowdfunding.controller.base.BaseController;

/**
 * <p>
 * 活动分类相关
 * </p>
 *
 * @author wushige
 * @date 2016-08-19 11:22
 */
@Controller
@RequestMapping("/activityTypes")
public class ActivityTypesController extends BaseController {

    /**
     * 加载分类列表视图
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadActivityTypesView() {
        return "activity/activity_type_list";
    }
}
