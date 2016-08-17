package team.wuxie.crowdfunding.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TGoodsType;
import team.wuxie.crowdfunding.exception.AjaxException;
import team.wuxie.crowdfunding.service.GoodsTypeService;
import team.wuxie.crowdfunding.util.DataTable;
import team.wuxie.crowdfunding.util.Page;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.i18n.Resources;
import team.wuxie.crowdfunding.util.validation.GoodsTypeValidator;
import team.wuxie.crowdfunding.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @InitBinder("goodsType")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new GoodsTypeValidator());
    }

    @Autowired
    GoodsTypeService goodsTypeService;

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
     * @param dataTable
     * @return
     */
    @RequestMapping(value = "/goodsTypePage", method = RequestMethod.GET)
    @ResponseBody
    public Page<TGoodsType> findGoodsTypePage(DataTable dataTable) {
        //定义列名
        String[] cols = {"type_id", "type_name", "type_img", "status", "create_time"};
        dataTable.setParams(cols, request);
        PageHelper.startPage(dataTable.getPageNum(), dataTable.getLength(), dataTable.getOrderBy());
        List<TGoodsType> list;
        if (!Strings.isNullOrEmpty(dataTable.getSearchValue())) {
            Map<String, String> map = Maps.newHashMap();
            map.put("typeName", dataTable.getSearchValue());
            list = goodsTypeService.selectAllLike(map);
        } else {
            list = goodsTypeService.selectAll();
        }
        PageInfo<TGoodsType> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dataTable.getDraw());
    }

    /**
     * 添加或者更新商品类型
     *
     * @param goodsType
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveGoodsType(@Valid @ModelAttribute("goodsType") TGoodsType goodsType, BindingResult result) throws AjaxException {
        if (result.hasErrors()) return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));

        try {
            goodsTypeService.insertOrUpdate(goodsType);
            return AjaxResult.getSuccess(Resources.getMessage("insert.success"));
        } catch (IllegalArgumentException e) {
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
        LOGGER.info(String.format("上/下架商品分类：userId=%s", String.valueOf(typeId)));
        try {
            goodsTypeService.updateStatus(typeId);
            return AjaxResult.getSuccess(Resources.getMessage("update.success"));
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
        LOGGER.info(String.format("删除商品分类：userId=%s", String.valueOf(typeId)));
        goodsTypeService.deleteById(typeId);
        return AjaxResult.getSuccess(Resources.getMessage("delete.success"));
    }
}
