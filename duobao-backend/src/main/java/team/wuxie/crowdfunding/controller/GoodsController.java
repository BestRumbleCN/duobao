package team.wuxie.crowdfunding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.service.GoodsService;

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

    @Autowired
    GoodsService goodsService;
}
