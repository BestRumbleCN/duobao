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
import org.springframework.web.multipart.MultipartFile;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TBanner;
import team.wuxie.crowdfunding.domain.enums.BannerType;
import team.wuxie.crowdfunding.service.BannerService;
import team.wuxie.crowdfunding.util.DtModel;
import team.wuxie.crowdfunding.util.Page;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.validation.BannerValidator;
import team.wuxie.crowdfunding.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * APP首页Banner控制器
 *
 * @author WuGang
 * @since 1.0
 */
@Controller
@RequestMapping("/banners")
public class BannersController extends BaseController {

    @InitBinder("banner")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(BannerValidator.validator());
    }

    @Autowired
    BannerService bannerService;

    /**
     * 加载Banner列表视图
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadBannersView(Model model) {
        model.addAttribute("bannerTypeMap", BannerType.asMap());
        return "banner/banner_list";
    }

    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<TBanner> findBannerPage(String table) {
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<TBanner> list;
        list = bannerService.selectAll();
        PageInfo<TBanner> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }

    /**
     * 创建Banner
     *
     * @param banner
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult newBanner(@Valid @ModelAttribute("banner") TBanner banner,
                                @RequestParam("file") MultipartFile file,
                                BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        }

        try {
            String img = uploadFileHandler(file);
            banner.newBanner(img);
            bannerService.insertSelective(banner);
            return AjaxResult.getSuccess("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("添加失败");
        }
    }

    /**
     * 加载Banner详情视图
     *
     * @param bannerId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{bannerId}", method = RequestMethod.GET)
    public String loadBannerDetailView(@PathVariable Integer bannerId, Model model) {
        TBanner banner = bannerService.selectById(bannerId);
        if (banner == null) {
            return redirect404();
        }
        model.addAttribute("banner", banner);
        model.addAttribute("bannerTypeMap", BannerType.asMap());
        return "banner/banner_detail";
    }

    /**
     * 编辑Banner
     *
     * @param banner
     * @param bannerId
     * @param result
     * @return
     */
    @RequestMapping(value = "/{bannerId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editBanner(@Valid @ModelAttribute("banner") TBanner banner,
                                 @PathVariable Integer bannerId,
                                 @RequestParam(value = "file", required = false) MultipartFile file,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        }

        try {
            if (!file.isEmpty()) {
                String img = uploadFileHandler(file);
                banner.setImg(img);
            }
            banner.updateBanner(bannerId);
            bannerService.updateSelective(banner);
            return AjaxResult.getSuccess("编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("编辑失败");
        }
    }

    /**
     * 修改Banner状态：上架/下架
     *
     * @param bannerId
     * @return
     */
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/{bannerId}/status", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult changeStatus(@PathVariable Integer bannerId) {
        TBanner banner = bannerService.selectById(bannerId);
        if (banner == null) {
            return AjaxResult.getFailure("Banner不存在");
        }
        try {
            banner.changeStatus();
            bannerService.updateSelective(banner);
            return AjaxResult.getSuccess("状态更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("状态更新失败");
        }
    }
}
