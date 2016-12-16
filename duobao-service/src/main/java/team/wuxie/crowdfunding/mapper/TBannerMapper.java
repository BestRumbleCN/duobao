package team.wuxie.crowdfunding.mapper;

import java.util.List;

import team.wuxie.crowdfunding.domain.TBanner;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

public interface TBannerMapper extends BaseMapper<TBanner> {
	List<TBanner> selectOnBanners();
}