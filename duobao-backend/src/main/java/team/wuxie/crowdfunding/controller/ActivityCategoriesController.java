package team.wuxie.crowdfunding.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TActivityCategory;
import team.wuxie.crowdfunding.service.ActivityCategoryService;
import team.wuxie.crowdfunding.util.DtModel;
import team.wuxie.crowdfunding.util.Page;

import java.util.List;

/**
 * <p>
 * 活动分类相关
 * </p>
 *
 * @author wushige
 * @date 2016-08-19 11:22
 */
@Controller
@RequestMapping("/activityCategories")
public class ActivityCategoriesController extends BaseController {

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
}
