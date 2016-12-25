package team.wuxie.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.domain.TArea;
import team.wuxie.crowdfunding.mapper.TAreaMapper;
import team.wuxie.crowdfunding.service.AreaService;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.List;

/**
 * ClassName:AreaServiceImpl <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年10月19日 上午8:48:40
 * @see
 */
@Service
@Transactional(readOnly = true)
public class AreaServiceImpl extends AbstractService<TArea> implements AreaService {

	@Autowired
	private TAreaMapper areMapper;

	@Override
	public List<TArea> findByParentId(Long parentId) {
		return areMapper.selectByParentId(parentId);
	}

	@Override
	public List<TArea> findProvinces() {
		Long CHINA = 86L;
		return findByParentId(CHINA);
	}

}
