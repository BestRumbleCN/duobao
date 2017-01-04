package team.wuxie.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.domain.TShippingRecord;
import team.wuxie.crowdfunding.domain.enums.ShippingStatus;
import team.wuxie.crowdfunding.domain.support.Shippings;
import team.wuxie.crowdfunding.exception.AjaxException;
import team.wuxie.crowdfunding.model.ShippingRecordQuery;
import team.wuxie.crowdfunding.service.ShippingRecordService;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.page.Page;

/**
 * 发货记录控制器
 *
 * @author WuGang
 * @since 1.0
 */
@Controller
@RequestMapping("/shippingRecords")
public class ShippingRecordsController extends BaseController {

    @Autowired
    private ShippingRecordService shippingRecordService;

    @RequestMapping(method = RequestMethod.GET)
    public String loadGoodsShippingRecordsView(Model model) {
        model.addAttribute("shippingStatusMap", ShippingStatus.asMap());
        return "goods/shipping_record_list";
    }

    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<TShippingRecord> findGoodsBidPage(String table, ShippingRecordQuery query) {
        return Shippings.findShippingRecordPage(table, query);
    }

    /**
     * 发货
     *
     * @param recordId
     * @return
     * @throws AjaxException
     */
    @RequestMapping(value = "/{recordId}/status", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult deliver(@PathVariable Integer recordId, TMessage message) throws AjaxException {
        shippingRecordService.deliver(recordId, message);
        return AjaxResult.getSuccess("发货成功");
    }
}
