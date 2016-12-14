package team.wuxie.crowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.wuxie.crowdfunding.domain.TBanner;
import team.wuxie.crowdfunding.mapper.TBannerMapper;
import team.wuxie.crowdfunding.service.BannerService;
import team.wuxie.crowdfunding.util.service.AbstractService;

@Service
public class BannerServiceImpl extends AbstractService<TBanner> implements BannerService {

	@Autowired
	private TBannerMapper bannerMapper;
	
	@Override
	public List<TBanner> selectOnBanners() {
		return bannerMapper.selectOnBanners();
	}

}
