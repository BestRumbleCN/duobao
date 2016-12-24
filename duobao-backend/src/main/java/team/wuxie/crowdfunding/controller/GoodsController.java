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
import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.domain.TGoodsType;
import team.wuxie.crowdfunding.domain.support.Goods;
import team.wuxie.crowdfunding.exception.AjaxException;
import team.wuxie.crowdfunding.exception.FileUploadException;
import team.wuxie.crowdfunding.model.GoodsQuery;
import team.wuxie.crowdfunding.service.GoodsService;
import team.wuxie.crowdfunding.service.GoodsTypeService;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.i18n.Resources;
import team.wuxie.crowdfunding.util.page.Page;
import team.wuxie.crowdfunding.util.validation.GoodsValidator;
import team.wuxie.crowdfunding.util.validation.ValidationUtil;
import team.wuxie.crowdfunding.vo.GoodsVO;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 商品Controller
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:34
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @InitBinder("goods")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(GoodsValidator.validator());
    }

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 加载商品列表视图
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadGoodsView(Model model) {
        List<TGoodsType> goodsTypes = Goods.findAllTypesByStatus(null);
        model.addAttribute("goodsTypes", goodsTypes);
        return "goods/goods_list";
    }

    /**
     * Ajax获取商品列表
     *
     * @param table
     * @return
     */
    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<GoodsVO> findGoodsPage(String table, GoodsQuery query) {
        return Goods.findGoodsVOPage(table, query);
    }

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveGoods(@Valid @ModelAttribute("goods") TGoods goods, BindingResult result) throws AjaxException {
        if (result.hasErrors())
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        try {
            goods.newGoods();
            goodsService.insertSelective(goods);
            return AjaxResult.getSuccess("添加成功");
        } catch (IllegalArgumentException | FileUploadException e) {
            return AjaxResult.getFailure(Resources.getMessage(e.getMessage()));
        }
    }

    /**
     * 加载商品详情视图
     *
     * @return
     */
    @RequestMapping(value = "/{goodsId}", method = RequestMethod.GET)
    public String loadGoodsDetailView(@PathVariable Integer goodsId, Model model) {
        TGoods goods = goodsService.selectById(goodsId);
        if (goods == null) return redirect404();
        model.addAttribute("goods", goods);
        return "goods/goods_detail";
    }

    /**
     * 更新商品
     *
     * @param goods
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{goodsId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateGoods(@PathVariable Integer goodsId,
                                  @Valid @ModelAttribute("goods") TGoods goods, BindingResult result) throws AjaxException {
        if (result.hasErrors())
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        try {
            goods.updateGoods(goodsId);
            goodsService.insertOrUpdate(goods);
            return AjaxResult.getSuccess("添加成功");
        } catch (IllegalArgumentException | FileUploadException e) {
            return AjaxResult.getFailure(Resources.getMessage(e.getMessage()));
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/pic", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult uploadPic(@RequestParam(value = "pic", required = false) MultipartFile pic) {
        if (pic != null) {
            String path = uploadFileHandler(pic);
            return AjaxResult.getSuccess(path);
        } else {
            return AjaxResult.getFailure("file null");
        }
    }

    /**
     * 更新商品：上/下架
     *
     * @param goodsId
     * @return
     * @throws AjaxException
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{goodsId}/status", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateStatus(@PathVariable Integer goodsId) throws AjaxException {
        try {
            goodsService.updateGoodsStatus(goodsId);
            return AjaxResult.getSuccess("状态更新成功");
        } catch (IllegalArgumentException e) {
            return AjaxResult.getFailure(Resources.getMessage(e.getMessage()));
        }
    }

    /**
     * 根据typeId删除商品
     *
     * @param goodsId
     * @return
     * @throws AjaxException
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{goodsId}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult deleteGoods(@PathVariable Integer goodsId) throws AjaxException {
        goodsService.deleteById(goodsId);
        return AjaxResult.getSuccess("删除成功");
    }
}
