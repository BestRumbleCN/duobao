package team.wuxie.crowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import team.wuxie.crowdfunding.controller.base.BaseController;

/**
 * @author WuGang
 * @since 1.0
 */
@Controller
@RequestMapping("/shippingRecords")
public class GoodsShippingController extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadGoodsShippingRecordsView() {
        return "goods/shipping_record_list";
    }
}
