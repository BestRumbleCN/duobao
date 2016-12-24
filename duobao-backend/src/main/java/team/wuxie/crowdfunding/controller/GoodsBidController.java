package team.wuxie.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.support.Goods;
import team.wuxie.crowdfunding.service.GoodsBidService;
import team.wuxie.crowdfunding.util.page.Page;

/**
 * 夺宝记录控制器
 *
 * @author WuGang
 * @since 1.0
 */
@Controller
@RequestMapping("/bidRecords")
public class GoodsBidController extends BaseController {

    @Autowired
    private GoodsBidService goodsBidService;

    @RequestMapping(method = RequestMethod.GET)
    public String loadGoodsBidListView() {
        return "goods/bid_record_list";
    }

    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<TGoodsBid> findGoodsBidPage(String table) {
        return Goods.findGoodsBidPage(table);
    }
}
