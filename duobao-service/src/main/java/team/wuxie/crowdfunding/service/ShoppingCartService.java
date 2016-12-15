package team.wuxie.crowdfunding.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import team.wuxie.crowdfunding.domain.TShoppingCart;
import team.wuxie.crowdfunding.util.service.BaseService;
import team.wuxie.crowdfunding.vo.ShoppingCartVO;

/**
 * ClassName:ShippingCartService <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年11月3日 下午8:00:57
 * @see 	 
 */
public interface ShoppingCartService extends BaseService<TShoppingCart> {
	
	/**
	 * 将商品加入到购物车
	 * @author fly
	 * @param userId
	 * @param goodsId  
	 * @since
	 */
	public void addGoods(Integer userId,Integer goodsId);
	
	/**
	 * 获取购物车内商品
	 * @author fly
	 * @param userId
	 * @return  
	 * @since
	 */
	@Transactional
	public List<ShoppingCartVO> getCartGoods(Integer userId);
	
	/**
	 * 获取购物车商品数量
	 * @author fly
	 * @param userId
	 * @return  
	 * @since
	 */
	public Integer countByUserId(Integer userId);
}

