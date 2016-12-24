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
import team.wuxie.crowdfunding.domain.TActivityCategory;
import team.wuxie.crowdfunding.service.ActivityCategoryService;
import team.wuxie.crowdfunding.util.page.DtModel;
import team.wuxie.crowdfunding.util.page.Page;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.validation.ActivityCategoryValidator;
import team.wuxie.crowdfunding.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 活动分类分类相关
 * </p>
 *
 * @author wushige
 * @date 2016-08-19 11:22
 */
@Controller
@RequestMapping("/activityCategories")
public class ActivityCategoriesController extends BaseController {

    @InitBinder("activityCategory")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(ActivityCategoryValidator.validator());
    }

    @Autowired
    ActivityCategoryService activityCategoryService;

    /**
     * 加载分类列表视图
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadActivityTypesView() {
        return "activity/activity_category_list";
    }

    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<TActivityCategory> findGoodsBidPage(String table) {
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<TActivityCategory> list;
        list = activityCategoryService.selectAll();
        PageInfo<TActivityCategory> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }

    /**
     * 创建活动分类
     *
     * @param activityCategory
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult newActivity(@Valid @ModelAttribute("activityCategory") TActivityCategory activityCategory, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        }

        try {
            activityCategory.newCategory();
            activityCategoryService.insertSelective(activityCategory);
            return AjaxResult.getSuccess("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("操作失败");
        }
    }

    /**
     * 加载活动分类详情视图
     *
     * @param categoryId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public String loadActivityDetailView(@PathVariable Integer categoryId, Model model) {
        TActivityCategory activityCategory = activityCategoryService.selectById(categoryId);
        if (activityCategory == null) {
            return redirect404();
        }
        model.addAttribute("activityCategory", activityCategory);
        return "activity/activity_category_detail";
    }

    /**
     * 编辑活动分类
     *
     * @param activityCategory
     * @param categoryId
     * @param result
     * @return
     */
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editActivity(@Valid @ModelAttribute("activityCategory") TActivityCategory activityCategory,
                                   @PathVariable Integer categoryId,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        }

        try {
            activityCategory.updateCategory(categoryId);
            activityCategoryService.updateSelective(activityCategory);
            return AjaxResult.getSuccess("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("操作失败");
        }
    }

    /**
     * 修改活动分类状态：上架/下架
     *
     * @param categoryId
     * @return
     */
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/{categoryId}/status", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult changeStatus(@PathVariable Integer categoryId) {
        TActivityCategory activityCategory = activityCategoryService.selectById(categoryId);
        if (activityCategory == null) {
            return AjaxResult.getFailure("activityCategory.error_none_found");
        }
        try {
            activityCategory.changeStatus();
            activityCategoryService.updateSelective(activityCategory);
            return AjaxResult.getSuccess("状态更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("操作失败");
        }
    }
}
