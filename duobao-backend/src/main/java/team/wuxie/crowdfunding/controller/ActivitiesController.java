package team.wuxie.crowdfunding.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TActivity;
import team.wuxie.crowdfunding.domain.TActivityCategory;
import team.wuxie.crowdfunding.model.ActivityQuery;
import team.wuxie.crowdfunding.service.ActivityCategoryService;
import team.wuxie.crowdfunding.service.ActivityService;
import team.wuxie.crowdfunding.util.DtModel;
import team.wuxie.crowdfunding.util.Page;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
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
            return AjaxResult.getSuccess("insert.success");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("operation.failure");
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
            return AjaxResult.getSuccess("update.success");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("operation.failure");
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
            return AjaxResult.getFailure("activity.error_none_found");
        }
        try {
            activity.changeStatus();
            activityService.updateSelective(activity);
            return AjaxResult.getSuccess("update.success");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("operation.failure");
        }
    }
}
