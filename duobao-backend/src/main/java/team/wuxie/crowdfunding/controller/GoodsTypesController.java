package team.wuxie.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TGoodsType;
import team.wuxie.crowdfunding.domain.support.Goods;
import team.wuxie.crowdfunding.exception.AjaxException;
import team.wuxie.crowdfunding.exception.FileUploadException;
import team.wuxie.crowdfunding.service.GoodsTypeService;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.i18n.Resources;
import team.wuxie.crowdfunding.util.page.Page;
import team.wuxie.crowdfunding.util.validation.GoodsTypeValidator;
import team.wuxie.crowdfunding.util.validation.ValidationUtil;

import javax.validation.Valid;

/**
 * <p>
 * 商品类型Controller
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:35
 */
@Controller
@RequestMapping("/goodsTypes")
public class GoodsTypesController extends BaseController {

    @InitBinder("goodsType")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(GoodsTypeValidator.validator());
    }

    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 加载商品类型列表视图
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadGoodsTypesView() {
        return "goods/goods_type_list";
    }

    /**
     * Ajax获取商品类型列表
     *
     * @param table
     * @return
     */
    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<TGoodsType> findGoodsTypePage(String table) {
        return Goods.findGoodsTypePage(table);
    }

    /**
     * 添加商品类型
     *
     * @param goodsType
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveGoodsType(@Valid @ModelAttribute("goodsType") TGoodsType goodsType,
                                    @RequestParam(required = false) MultipartFile file, BindingResult result) throws AjaxException {
        if (result.hasErrors()) return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        if (goodsType.getTypeId() == null && file == null)
            return AjaxResult.getFailure("请选择商品分类图片");

        try {
            if (file != null) {
                String path = uploadFileHandler(file);
                goodsType.setTypeImg(path);
            }
            goodsType.newGoodsType();
            goodsTypeService.insertSelective(goodsType);
            return AjaxResult.getSuccess("添加成功");
        } catch (IllegalArgumentException | FileUploadException e) {
            return AjaxResult.getFailure(Resources.getMessage(e.getMessage()));
        }
    }

    /**
     * 加载商品类型详情视图
     *
     * @return
     */
    @RequestMapping(value = "/{typeId}", method = RequestMethod.GET)
    public String loadGoodsTypeDetailView(@PathVariable Integer typeId, Model model) {
        TGoodsType goodsType = Goods.selectTypeById(typeId);
        if (goodsType == null) return redirect404();
        model.addAttribute("goodsType", goodsType);
        return "goods/goods_type_detail";
    }

    /**
     * 更新商品类型
     *
     * @param goodsType
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{typeId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveGoodsType(@PathVariable Integer typeId,
                                    @Valid @ModelAttribute("goodsType") TGoodsType goodsType,
                                    @RequestParam(required = false) MultipartFile file, BindingResult result) throws AjaxException {
        if (result.hasErrors()) return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));

        try {
            if (file != null) {
                String path = uploadFileHandler(file);
                goodsType.setTypeImg(path);
            }
            goodsType.updateGoodsType(typeId);
            goodsTypeService.updateSelective(goodsType);
            return AjaxResult.getSuccess("修改成功");
        } catch (IllegalArgumentException | FileUploadException e) {
            return AjaxResult.getFailure(Resources.getMessage(e.getMessage()));
        }
    }

    /**
     * 更新商品类型状态：上/下架
     *
     * @param typeId
     * @return
     * @throws AjaxException
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{typeId}/status", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateStatus(@PathVariable Integer typeId) throws AjaxException {
        try {
            goodsTypeService.updateStatus(typeId);
            return AjaxResult.getSuccess("状态修改成功");
        } catch (IllegalArgumentException e) {
            return AjaxResult.getFailure(Resources.getMessage(e.getMessage()));
        }
    }

    /**
     * 根据typeId删除商品类型
     *
     * @param typeId
     * @return
     * @throws AjaxException
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{typeId}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult deleteGoodsType(@PathVariable Integer typeId) throws AjaxException {
        goodsTypeService.deleteById(typeId);
        return AjaxResult.getSuccess("删除成功");
    }
}
