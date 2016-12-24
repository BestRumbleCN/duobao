package team.wuxie.crowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.enums.BidStatus;
import team.wuxie.crowdfunding.domain.support.Goods;
import team.wuxie.crowdfunding.model.GoodsBidQuery;
import team.wuxie.crowdfunding.util.page.Page;
import team.wuxie.crowdfunding.vo.GoodsBidVO;

/**
 * 夺宝记录控制器
 *
 * @author WuGang
 * @since 1.0
 */
@Controller
@RequestMapping("/bidRecords")
public class GoodsBidController extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadGoodsBidListView(Model model) {
        model.addAttribute("bidStatusMap", BidStatus.asMap());
        return "goods/bid_record_list";
    }

    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<GoodsBidVO> findGoodsBidPage(String table, GoodsBidQuery query) {
        return Goods.findGoodsBidVOPage(table, query);
    }
}
