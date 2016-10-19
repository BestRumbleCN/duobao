package team.wuxie.crowdfunding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import team.wuxie.crowdfunding.domain.TShippingAddress;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

public interface TShippingAddressMapper extends BaseMapper<TShippingAddress> {
	List<TShippingAddress> selectByUserId(Integer userId);
	
	TShippingAddress selectDefaultByUserId(Integer userId);
	
	void deleteByAddressId(@Param("addressId")Integer addressId,@Param("userId") Integer userId);
}