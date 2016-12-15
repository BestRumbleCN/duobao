package team.wuxie.crowdfunding.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import team.wuxie.crowdfunding.exception.AjaxException;
import team.wuxie.crowdfunding.exception.FileUploadException;
import team.wuxie.crowdfunding.model.GoodsQuery;
import team.wuxie.crowdfunding.ro.goods.GoodsConverter;
import team.wuxie.crowdfunding.ro.goods.GoodsRO;
import team.wuxie.crowdfunding.service.GoodsService;
import team.wuxie.crowdfunding.service.GoodsTypeService;
import team.wuxie.crowdfunding.util.DtModel;
import team.wuxie.crowdfunding.util.Page;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.i18n.Resources;
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

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @InitBinder("goods")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new GoodsValidator());
    }

    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsTypeService goodsTypeService;

    /**
     * 加载商品列表视图
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadGoodsView(Model model) {
        TGoodsType goodsType = new TGoodsType();
        goodsType.setStatus(true);
        List<TGoodsType> goodsTypes = goodsTypeService.select(goodsType);
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
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<GoodsVO> list;
        list = goodsService.selectVOAll(query);
        PageInfo<GoodsVO> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }

    /**
     * 添加或者更新商品
     *
     * @param goods
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveGoods(@Valid @ModelAttribute("goods") GoodsRO goods, BindingResult result) throws AjaxException {
        if (result.hasErrors())
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        try {
            goodsService.insertOrUpdate(new GoodsConverter().ROconverteTo(goods));
            return AjaxResult.getSuccess(Resources.getMessage("insert.success"));
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
        LOGGER.info(String.format("上/下架商品：goodsId=%s", String.valueOf(goodsId)));
        try {
            goodsService.updateGoodsStatus(goodsId);
            return AjaxResult.getSuccess(Resources.getMessage("update.success"));
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
        LOGGER.info(String.format("删除商品：goodsId=%s", String.valueOf(goodsId)));
        goodsService.deleteById(goodsId);
        return AjaxResult.getSuccess(Resources.getMessage("delete.success"));
    }
}
