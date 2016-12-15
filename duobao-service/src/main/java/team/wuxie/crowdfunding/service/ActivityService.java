package team.wuxie.crowdfunding.service;

import team.wuxie.crowdfunding.domain.TActivity;
import team.wuxie.crowdfunding.model.ActivityQuery;
import team.wuxie.crowdfunding.util.service.BaseService;

import java.util.List;

/**
 * @author WuGang
 * @since 1.0
 */
public interface ActivityService extends BaseService<TActivity> {

    List<TActivity> selectAll(ActivityQuery query);
}
