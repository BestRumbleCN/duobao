package team.wuxie.crowdfunding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.wuxie.crowdfunding.domain.TShoppingCart;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.mapper.TShoppingCartMapper;
import team.wuxie.crowdfunding.service.ShoppingCartService;
import team.wuxie.crowdfunding.util.service.AbstractService;
import team.wuxie.crowdfunding.vo.GoodsBidVO;
import team.wuxie.crowdfunding.vo.ShoppingCartVO;

/**
 * ClassName:ShoppingCartServiceImpl <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月3日 下午8:13:18
 * @see
 */
@Service
public class ShoppingCartServiceImpl extends AbstractService<TShoppingCart> implements ShoppingCartService {

	@Autowired
	private TShoppingCartMapper shoppingCartMapper;
	@Autowired
	private TGoodsBidMapper goodsBidMapper;

	@Override
	public void addGoods(Integer userId, Integer goodsId) {
		TShoppingCart cart = new TShoppingCart(null, userId, goodsId, null, null);
		this.insertSelective(cart);
	}

	@Override
	public List<ShoppingCartVO> getCartGoods(Integer userId) {
		List<TShoppingCart> shoppingCartList = shoppingCartMapper.selectByUserId(userId);
		List<ShoppingCartVO> cartVos = new ArrayList<ShoppingCartVO>();
		for (TShoppingCart cart : shoppingCartList) {
			GoodsBidVO bidVo = goodsBidMapper.selectLastByGoodsId(cart.getGoodsId());
			if (bidVo == null) {
				shoppingCartMapper.deleteByPrimaryKey(cart.getCartId());
				continue;
			}
			ShoppingCartVO cartVo = new ShoppingCartVO(cart.getCartId(), cart.getGoodsId(), bidVo.getBidId(),
					bidVo.getImg(), bidVo.getTotalAmount(), bidVo.getJoinAmount(), bidVo.getGoodsName(),
					bidVo.getChannel(), bidVo.getTypeId());
			cartVos.add(cartVo);
		}
		return cartVos;
	}

	@Override
	public Integer countByUserId(Integer userId) {
		TShoppingCart search = new TShoppingCart();
		search.setUserId(userId);;
		return this.selectCount(search);
	}

}
