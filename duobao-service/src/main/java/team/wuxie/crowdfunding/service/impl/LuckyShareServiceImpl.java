package team.wuxie.crowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.github.pagehelper.PageHelper;

import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.TLuckyShare;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.mapper.TGoodsMapper;
import team.wuxie.crowdfunding.mapper.TLuckyShareMapper;
import team.wuxie.crowdfunding.service.LuckyShareService;
import team.wuxie.crowdfunding.util.service.AbstractService;

@Service
public class LuckyShareServiceImpl extends AbstractService<TLuckyShare>
		implements LuckyShareService {
	@Autowired
	private TLuckyShareMapper luckyShareMapper;
	@Autowired
	private TGoodsBidMapper goodsBidMapper;
	@Autowired
	private TGoodsMapper goodsMapper;

	@Override
	public boolean addLuckyShare(String comment, String imgs, Integer bidId,
			Integer userId) throws IllegalArgumentException {
		Assert.hasText(comment, "评论不能为空哦");
		Assert.hasText(imgs, "请至少上传一张图片");
		TGoodsBid goodsBid = goodsBidMapper.selectByPrimaryKey(bidId);
		Assert.notNull(goodsBid, "商品期号不存在");
		TGoods goods = goodsMapper.selectByPrimaryKey(goodsBid.getGoodsId());
		Assert.isTrue(userId == goodsBid.getWinnerId(), "您不是这期的幸运用户");
		TLuckyShare share = new TLuckyShare(null, userId, bidId,goods.getGoodsId(),
				goods.getGoodsName(), goodsBid.getLuckyNum() + "", comment,
				goodsBid.getJoinAmount(), imgs, goodsBid.getPublishTime(), null);
		return insertSelective(share);
	}

	@Override
	public List<TLuckyShare> selectByUserId(Integer userId, Integer pageNum,
			Integer pageSize) {
		setPageOrder(pageNum, pageSize);
		return luckyShareMapper.selectByUserId(userId);
	}

	@Override
	public List<TLuckyShare> selectByGoodsId(Integer goodsId, Integer pageNum,
			Integer pageSize) {
		setPageOrder(pageNum, pageSize);
		return luckyShareMapper.selectByGoodsId(goodsId);
	}

	@Override
	public List<TLuckyShare> selectAll(Integer pageNum, Integer pageSize) {
		setPageOrder(pageNum, pageSize);
		return luckyShareMapper.selectAll();
	}

	private void setPageOrder(Integer pageNum, Integer pageSize){
		PageHelper.startPage(pageNum, pageSize, true, false);
		PageHelper.orderBy("bid_id desc");
	}
}
