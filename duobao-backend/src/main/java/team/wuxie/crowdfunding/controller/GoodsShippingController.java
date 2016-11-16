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
import team.wuxie.crowdfunding.domain.TShippingRecord;
import team.wuxie.crowdfunding.model.ShippingRecordQuery;
import team.wuxie.crowdfunding.service.ShippingRecordService;
import team.wuxie.crowdfunding.util.DtModel;
import team.wuxie.crowdfunding.util.Page;

import java.util.List;

/**
 * 发货记录控制器
 *
 * @author WuGang
 * @since 1.0
 */
@Controller
@RequestMapping("/shippingRecords")
public class GoodsShippingController extends BaseController {

    @Autowired
    ShippingRecordService shippingRecordService;

    @RequestMapping(method = RequestMethod.GET)
    public String loadGoodsShippingRecordsView() {
        return "goods/shipping_record_list";
    }

    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<TShippingRecord> findGoodsBidPage(String table, ShippingRecordQuery query) {
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<TShippingRecord> list;
        list = shippingRecordService.selectAll(query);
        PageInfo<TShippingRecord> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }
}
