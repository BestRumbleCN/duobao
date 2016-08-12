package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.TIntegral;
import team.wuxie.crowdfunding.mapper.TIntegralMapper;
import team.wuxie.crowdfunding.service.IntegralService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * <p>
 * 积分明细ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:27
 */
@Service
public class IntegralServiceImpl extends AbstractService<TIntegral> implements IntegralService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    TIntegralMapper integralMapper;
}