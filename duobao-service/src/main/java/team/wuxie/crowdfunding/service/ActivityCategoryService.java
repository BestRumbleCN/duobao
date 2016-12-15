package team.wuxie.crowdfunding.service;

import team.wuxie.crowdfunding.domain.TActivityCategory;
import team.wuxie.crowdfunding.util.service.BaseService;

import java.util.List;

/**
 * @author WuGang
 * @since 1.0
 */
public interface ActivityCategoryService extends BaseService<TActivityCategory> {

    List<TActivityCategory> selectByEnabled(Boolean enabled);
}
