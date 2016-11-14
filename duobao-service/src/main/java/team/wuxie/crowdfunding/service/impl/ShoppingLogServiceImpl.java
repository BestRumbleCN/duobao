package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.TShoppingLog;
import team.wuxie.crowdfunding.service.ShoppingLogService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * @author WuGang
 * @since 1.0
 */
@Service
public class ShoppingLogServiceImpl extends AbstractService<TShoppingLog> implements ShoppingLogService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());
}
