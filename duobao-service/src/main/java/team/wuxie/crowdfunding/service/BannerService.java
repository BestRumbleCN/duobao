package team.wuxie.crowdfunding.service;

import java.util.List;

import team.wuxie.crowdfunding.domain.TBanner;
import team.wuxie.crowdfunding.util.service.BaseService;

public interface BannerService extends BaseService<TBanner> {
	List<TBanner> selectOnBanners();
}
