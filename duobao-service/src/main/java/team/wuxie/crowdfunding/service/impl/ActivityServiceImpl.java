package team.wuxie.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.TActivity;
import team.wuxie.crowdfunding.mapper.TActivityMapper;
import team.wuxie.crowdfunding.model.ActivityQuery;
import team.wuxie.crowdfunding.service.ActivityService;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.List;

/**
 * @author WuGang
 * @since 1.0
 */
@Service
public class ActivityServiceImpl extends AbstractService<TActivity> implements ActivityService {

    @Autowired
    TActivityMapper tActivityMapper;

    @Override
    public List<TActivity> selectAll(ActivityQuery query) {
        return tActivityMapper.selectAllByQuery(query);
    }
}
