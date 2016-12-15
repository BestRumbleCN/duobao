package team.wuxie.crowdfunding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import team.wuxie.crowdfunding.domain.TRedPocket;
import team.wuxie.crowdfunding.domain.enums.PocketStatus;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;
import team.wuxie.crowdfunding.vo.RedPocketVo;

public interface TRedPocketMapper extends BaseMapper<TRedPocket> {
	List<RedPocketVo> selectByUserIdAndStatus(@Param("userId") Integer userId, @Param("status") PocketStatus status);
}