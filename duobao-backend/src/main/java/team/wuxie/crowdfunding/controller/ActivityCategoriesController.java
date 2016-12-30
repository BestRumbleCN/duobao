package team.wuxie.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TActivityCategory;
import team.wuxie.crowdfunding.domain.support.Activities;
import team.wuxie.crowdfunding.service.ActivityCategoryService;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.page.Page;
import team.wuxie.crowdfunding.util.validation.ActivityCategoryValidator;
import team.wuxie.crowdfunding.util.validation.ValidationUtil;

import javax.validation.Valid;

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
    private ActivityCategoryService activityCategoryService;

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
    public Page<TActivityCategory> findActivityCategoriesPage(String table) {
        return Activities.findActivityCategoryPage(table);
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
    public AjaxResult newActivity(@Valid @ModelAttribute("activityCategory") TActivityCategory activityCategory,
                                  @RequestParam(required = false) MultipartFile file,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        }

        try {
            if (file != null && !file.isEmpty()) {
                String path = uploadFileHandler(file);
                activityCategory.setImg(path);
            }
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
        TActivityCategory activityCategory = Activities.selectCategoryById(categoryId);
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
                                   @RequestParam(required = false) MultipartFile file,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        }

        try {
            if (file != null && !file.isEmpty()) {
                String path = uploadFileHandler(file);
                activityCategory.setImg(path);
            }
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
        TActivityCategory activityCategory = Activities.selectCategoryById(categoryId);
        if (activityCategory == null) {
            return AjaxResult.getFailure("activityCategory.not_found");
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
