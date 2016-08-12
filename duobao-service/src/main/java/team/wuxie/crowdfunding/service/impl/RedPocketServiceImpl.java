package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.TRedPocket;
import team.wuxie.crowdfunding.mapper.TRedPocketMapper;
import team.wuxie.crowdfunding.service.RedPocketService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * <p>
 * 红包明细ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:31
 */
@Service
public class RedPocketServiceImpl extends AbstractService<TRedPocket> implements RedPocketService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    TRedPocketMapper redPocketMapper;
}
