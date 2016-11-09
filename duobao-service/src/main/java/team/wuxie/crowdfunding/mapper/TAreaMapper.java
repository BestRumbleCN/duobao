package team.wuxie.crowdfunding.mapper;

import java.util.List;

import team.wuxie.crowdfunding.domain.TArea;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

public interface TAreaMapper extends BaseMapper<TArea> {
	
	List<TArea> selectByParentId(Long parentId);
}