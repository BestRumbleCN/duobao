package team.wuxie.crowdfunding.service.impl;

import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.TActivityCategory;
import team.wuxie.crowdfunding.service.ActivityCategoryService;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.List;

/**
 * @author WuGang
 * @since 1.0
 */
@Service
public class ActivityCategoryServiceImpl extends AbstractService<TActivityCategory> implements ActivityCategoryService {

    @Override
    public List<TActivityCategory> selectByEnabled(Boolean enabled) {
        TActivityCategory activityCategory = new TActivityCategory();
        activityCategory.setEnabled(enabled);
        return select(activityCategory);
    }
}
