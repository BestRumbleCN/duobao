package team.wuxie.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TActivity;
import team.wuxie.crowdfunding.domain.TActivityCategory;
import team.wuxie.crowdfunding.domain.support.Activities;
import team.wuxie.crowdfunding.model.ActivityQuery;
import team.wuxie.crowdfunding.service.ActivityCategoryService;
import team.wuxie.crowdfunding.service.ActivityService;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.page.Page;
import team.wuxie.crowdfunding.util.validation.ActivityValidator;
import team.wuxie.crowdfunding.util.validation.ValidationUtil;

import javax.validation.Valid;
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

    @InitBinder("activity")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(ActivityValidator.validator());
    }

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityCategoryService activityCategoryService;

    /**
     * 加载活动视图
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadActivitiesView(Model model) {
        List<TActivityCategory> categories = Activities.findAllCategories(null);
        model.addAttribute("categories", categories);
        return "activity/activity_list";
    }

    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<TActivity> findActivitiesPage(String table, ActivityQuery query) {
        return Activities.findActivityPage(table, query);
    }

    /**
     * 创建活动
     *
     * @param activity
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult newActivity(@Valid @ModelAttribute("activity") TActivity activity, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        }

        try {
            activity.newActivity();
            activityService.insertSelective(activity);
            return AjaxResult.getSuccess("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("操作失败");
        }
    }

    /**
     * 加载活动详情视图
     *
     * @param activityId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{activityId}", method = RequestMethod.GET)
    public String loadActivityDetailView(@PathVariable Integer activityId, Model model) {
        TActivity activity = activityService.selectById(activityId);
        if (activity == null) {
            return redirect404();
        }
        model.addAttribute("activity", activity);
        return "activity/activity_detail";
    }

    /**
     * 编辑活动
     *
     * @param activity
     * @param activityId
     * @param result
     * @return
     */
    @RequestMapping(value = "/{activityId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editActivity(@Valid @ModelAttribute("activity") TActivity activity,
                                   @PathVariable Integer activityId,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        }

        try {
            activity.updateActivity(activityId);
            activityService.updateSelective(activity);
            return AjaxResult.getSuccess("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("操作失败");
        }
    }

    /**
     * 修改活动状态：上架/下架
     *
     * @param activityId
     * @return
     */
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/{activityId}/status", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult changeStatus(@PathVariable Integer activityId) {
        TActivity activity = activityService.selectById(activityId);
        if (activity == null) {
            return AjaxResult.getFailure("活动不存在");
        }
        try {
            activity.changeStatus();
            activityService.updateSelective(activity);
            return AjaxResult.getSuccess("状态更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("操作失败");
        }
    }
}
