package team.wuxie.crowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import team.wuxie.crowdfunding.domain.BidStatus;
import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.service.GoodsBidService;
import team.wuxie.crowdfunding.util.service.AbstractService;
import team.wuxie.crowdfunding.vo.GoodsBidVO;

/**
 * ClassName:GoodsBidServiceImpl <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年10月24日 下午5:00:26
 * @see
 */
@Service
public class GoodsBidServiceImpl extends AbstractService<TGoodsBid> implements GoodsBidService {

	@Autowired
	private TGoodsBidMapper goodsBidMapper;

	@Override
	public boolean generateAndAdd(TGoods goods) throws IllegalArgumentException {
		TGoodsBid goodsBid = goodsBidMapper.selectLastByGoodsId(goods.getGoodsId());
		Assert.isTrue(goodsBid == null || !goodsBid.getBidStatus().sameValueAs(BidStatus.PUBLISHED),
				"goodsbid already existed");
		TGoodsBid bid = new TGoodsBid(null, goods.getGoodsId(), goods.getTotalAmount(), goods.getSinglePrice(),
				BidStatus.RUNNING, null, null, null, null, null,goods.getSinglePrice());
		return insertSelective(bid);
	}

	@Override
	public List<GoodsBidVO> selectByType(Integer queryType) throws IllegalArgumentException{
		if (queryType == 1 || queryType == 2) {
			return goodsBidMapper.selectVOsByChannel(queryType);
		}
		return null;
	}

}
