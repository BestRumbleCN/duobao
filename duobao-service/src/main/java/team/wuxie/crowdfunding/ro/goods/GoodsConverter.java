package team.wuxie.crowdfunding.ro.goods;

import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.ro.AbstractConverter;

/**
 * ClassName:GoodsConverter <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月20日 下午8:25:22
 * @see 	 
 */
public class GoodsConverter extends AbstractConverter<GoodsRO, TGoods> {

	@Override
	public GoodsRO getROInstatnce() {
		return new GoodsRO();
	}

	@Override
	public TGoods getInstance() {
		return new TGoods();
	}
	
	
}

