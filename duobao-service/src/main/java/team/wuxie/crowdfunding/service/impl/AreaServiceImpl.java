package team.wuxie.crowdfunding.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.wuxie.crowdfunding.domain.TArea;
import team.wuxie.crowdfunding.mapper.TAreaMapper;
import team.wuxie.crowdfunding.service.AreaService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * ClassName:AreaServiceImpl <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年10月19日 上午8:48:40
 * @see
 */
@Service
public class AreaServiceImpl extends AbstractService<TArea> implements AreaService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	TAreaMapper areMapper;

	@Override
	public List<TArea> findByParentId(Long parentId) {
		return areMapper.selectByParentId(parentId);
	}

	@Override
	public List<TArea> findProvinces() {
		Long CHINA = 86l;
		return findByParentId(CHINA);
	}

}
