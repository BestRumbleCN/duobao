package team.wuxie.crowdfunding.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import team.wuxie.crowdfunding.domain.TArea;
import team.wuxie.crowdfunding.domain.TShippingAddress;
import team.wuxie.crowdfunding.mapper.TAreaMapper;
import team.wuxie.crowdfunding.mapper.TShippingAddressMapper;
import team.wuxie.crowdfunding.service.ShippingAddressService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * ClassName:ShippingAddressServiceImpl <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年10月19日 下午2:04:49
 * @see
 */
@Service
public class ShippingAddressServiceImpl extends AbstractService<TShippingAddress> implements ShippingAddressService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	TShippingAddressMapper shippingAddressMapper;

	@Autowired
	TAreaMapper areaMapper;

	@Override
	public List<TShippingAddress> selectByUserId(Integer userId) {
		return shippingAddressMapper.selectByUserId(userId);
	}

	@Override
	public TShippingAddress selectDefaultByUserId(Integer userId) {
		return shippingAddressMapper.selectDefaultByUserId(userId);
	}

	@Override
	public boolean insertOrUpdate(TShippingAddress shippingAddress, Integer modifyId) throws IllegalArgumentException {
		shippingAddress.setUpdateId(modifyId);
		Assert.hasText(shippingAddress.getName(), "收件人不能为空");
		Assert.hasText(shippingAddress.getCellphone(), "收件人手机不能为空");
		Assert.hasText(shippingAddress.getAddress(), "详细地址不能为空");
		Assert.notNull(shippingAddress.getProvinceId(), "省份不能为空");
		Assert.notNull(shippingAddress.getCityId(), "市不能为空");
		Assert.notNull(shippingAddress.getPrefectureId(), "区县不能为空");
		// 新增
		if (shippingAddress.getAddressId() == null) {
			List<TShippingAddress> existAdds = shippingAddressMapper.selectByUserId(shippingAddress.getUserId());
			Assert.isTrue(existAdds.size() <= 3, "奖品地址不能超过3个");
			Assert.isNull(shippingAddress.getAddressId(), "新增地址ID应为空");
			if (shippingAddress.getStreetId() != null) {
				TArea area = areaMapper.selectByPrimaryKey(shippingAddress.getStreetId());
				Assert.notNull(area, "街道不存在");
				shippingAddress
						.setBaseAddress(area.getProvince() + area.getCity() + area.getDistrict() + area.getName());
			} else {
				TArea area = areaMapper.selectByPrimaryKey(shippingAddress.getPrefectureId());
				Assert.notNull(area, "区/县不存在");
				shippingAddress.setBaseAddress(area.getProvince() + area.getCity() + area.getDistrict());
			}
			shippingAddress.setUpdateId(modifyId);
			shippingAddress.setCreateId(modifyId);
			removeDefault(shippingAddress);
			return insertSelective(shippingAddress);
		} else {
			// 修改
			removeDefault(shippingAddress);
			return updateSelective(shippingAddress);
		}
	}

	/**
	 * 取消旧的默认地址
	 * 
	 * @author fly
	 * @param newAddress
	 * @since
	 */
	private void removeDefault(TShippingAddress newAddress) {
		if (newAddress.getIsDefault() == null && !newAddress.getIsDefault()) {
			return;
		}
		TShippingAddress defaultAdd = shippingAddressMapper.selectDefaultByUserId(newAddress.getUserId());
		if (defaultAdd != null && defaultAdd.getIsDefault()) {
			defaultAdd.setIsDefault(false);
			updateSelective(defaultAdd);
		}
	}

	@Override
	public void deleteByAddressId(Integer addressId, Integer userId) {
		shippingAddressMapper.deleteByAddressId(addressId, userId);
	}
	

}
