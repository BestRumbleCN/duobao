package team.wuxie.crowdfunding.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TActivity;
import team.wuxie.crowdfunding.domain.TActivityCategory;
import team.wuxie.crowdfunding.model.ActivityQuery;
import team.wuxie.crowdfunding.service.ActivityCategoryService;
import team.wuxie.crowdfunding.service.ActivityService;
import team.wuxie.crowdfunding.util.DtModel;
import team.wuxie.crowdfunding.util.Page;

import java.util.List;

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

    @Autowired
    ActivityService activityService;
    @Autowired
    ActivityCategoryService activityCategoryService;

    /**
     * 加载活动视图
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadActivitiesView(Model model) {
        List<TActivityCategory> categories = activityCategoryService.selectByEnabled(true);
        model.addAttribute("categories", categories);
        return "activity/activity_list";
    }

    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<TActivity> findActivitiesPage(String table, ActivityQuery query) {
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<TActivity> list;
        list = activityService.selectAll(query);
        PageInfo<TActivity> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }
}
