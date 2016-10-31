package team.wuxie.crowdfunding.service;

import java.util.List;

import team.wuxie.crowdfunding.domain.TArea;
import team.wuxie.crowdfunding.util.service.BaseService;

/**
 * ClassName:AreaService <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月19日 上午8:46:45
 * @see 	 
 */
public interface AreaService extends BaseService<TArea> {
	
	List<TArea> findByParentId(Long parentId);
	
	List<TArea> findProvinces();
}

