package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.domain.enums.IntegralType;
import team.wuxie.crowdfunding.domain.TIntegral;
import team.wuxie.crowdfunding.mapper.TIntegralMapper;
import team.wuxie.crowdfunding.service.IntegralService;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.Date;

/**
 * <p>
 * 积分明细ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:27
 */
@Service
@Transactional(readOnly = true)
public class IntegralServiceImpl extends AbstractService<TIntegral> implements IntegralService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    TIntegralMapper integralMapper;

    @Override
    @Transactional
    public boolean insert(Integer userId, IntegralType integralType, boolean inOut, Integer amount) throws IllegalArgumentException {
        TIntegral integral = new TIntegral(userId, integralType, inOut, amount);
        integral.setCreateTime(new Date());
        return insertSelective(integral);
    }
}
