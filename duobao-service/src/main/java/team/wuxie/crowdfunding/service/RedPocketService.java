package team.wuxie.crowdfunding.service;

import java.util.List;

import team.wuxie.crowdfunding.domain.TRedPocket;
import team.wuxie.crowdfunding.domain.enums.PocketStatus;
import team.wuxie.crowdfunding.util.service.BaseService;
import team.wuxie.crowdfunding.vo.RedPocketVo;

/**
 * <p>
 * 红包明细Service
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:31
 */
public interface RedPocketService extends BaseService<TRedPocket> {
	List<RedPocketVo> selectByUserIdAndStatus(Integer userId, PocketStatus status);
}
