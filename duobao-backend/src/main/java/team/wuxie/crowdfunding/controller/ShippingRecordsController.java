package team.wuxie.crowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TShippingRecord;
import team.wuxie.crowdfunding.domain.enums.ShippingStatus;
import team.wuxie.crowdfunding.domain.support.Shippings;
import team.wuxie.crowdfunding.model.ShippingRecordQuery;
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
}
