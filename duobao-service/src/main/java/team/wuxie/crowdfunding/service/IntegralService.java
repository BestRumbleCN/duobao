package team.wuxie.crowdfunding.service;

import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.domain.IntegralType;
import team.wuxie.crowdfunding.domain.TIntegral;
import team.wuxie.crowdfunding.util.service.BaseService;

/**
 * <p>
 * 积分明细Service
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:27
 */
public interface IntegralService extends BaseService<TIntegral> {

    /**
     * 添加积分明细
     * @param userId
     * @param integralType
     * @param inOut
     * @param amount
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    boolean insert(Integer userId, IntegralType integralType, boolean inOut, Integer amount) throws IllegalArgumentException;
}
