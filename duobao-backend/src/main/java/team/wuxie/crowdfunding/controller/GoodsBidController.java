package team.wuxie.crowdfunding.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.service.GoodsBidService;
import team.wuxie.crowdfunding.util.page.DtModel;
import team.wuxie.crowdfunding.util.page.Page;

import java.util.List;

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
    GoodsBidService goodsBidService;

    @RequestMapping(method = RequestMethod.GET)
    public String loadGoodsBidListView() {
        return "goods/bid_record_list";
    }

    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<TGoodsBid> findGoodsBidPage(String table) {
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<TGoodsBid> list;
        list = goodsBidService.selectAll();
        PageInfo<TGoodsBid> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }
}
