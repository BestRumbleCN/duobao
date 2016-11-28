package team.wuxie.crowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.wuxie.crowdfunding.domain.TRedPocket;
import team.wuxie.crowdfunding.domain.enums.PocketStatus;
import team.wuxie.crowdfunding.mapper.TRedPocketMapper;
import team.wuxie.crowdfunding.service.RedPocketService;
import team.wuxie.crowdfunding.util.service.AbstractService;
import team.wuxie.crowdfunding.vo.RedPocketVo;

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

   // private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    TRedPocketMapper redPocketMapper;

	@Override
	public List<RedPocketVo> selectByUserIdAndStatus(Integer userId, PocketStatus status) {
		return redPocketMapper.selectByUserIdAndStatus(userId, status);
	}
}
