package team.wuxie.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.domain.TBanner;
import team.wuxie.crowdfunding.mapper.TBannerMapper;
import team.wuxie.crowdfunding.service.BannerService;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BannerServiceImpl extends AbstractService<TBanner> implements BannerService {

	@Autowired
	private TBannerMapper bannerMapper;
	
	@Override
	public List<TBanner> selectOnBanners() {
		return bannerMapper.selectOnBanners();
	}

}
