package team.wuxie.crowdfunding.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import team.wuxie.crowdfunding.domain.TShippingAddress;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.util.service.BaseService;

/**
 * ClassName:ShippingAddressService <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月19日 下午2:01:10
 * @see 	 
 */
public interface ShippingAddressService extends BaseService<TShippingAddress> {
	/**
	 * 查询所有地址
	 * @author fly
	 * @param userId
	 * @return  
	 * @since
	 */
	List<TShippingAddress> selectByUserId(Integer userId);
	
	/**
	 * 查询默认收货地址
	 * @author fly
	 * @param userId
	 * @return  
	 * @since
	 */
	TShippingAddress selectDefaultByUserId(Integer userId);
	
//	/**
//	 * @author fly
//	 * @param shippingAddress
//	 * @return
//	 * @throws IllegalArgumentException  
//	 * @since
//	 */
//	@Transactional
//    boolean insertOrUpdate(TShippingAddress shippingAddress) throws IllegalArgumentException;
	
	/**
	 * @author fly
	 * @param shippingAddress
	 * @param modifyId	修改人ID
	 * @return
	 * @throws IllegalArgumentException  
	 * @since
	 */
	@Transactional
	boolean insertOrUpdate(TShippingAddress shippingAddress,Integer modifyId)throws IllegalArgumentException;
	
	/**
	 * 
	 * @author fly
	 * @param addressId
	 * @param userId
	 * @return  
	 * @since
	 */
	void deleteByAddressId(Integer addressId, Integer userId);
	
}

