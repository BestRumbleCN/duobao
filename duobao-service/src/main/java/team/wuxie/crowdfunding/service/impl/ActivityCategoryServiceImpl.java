package team.wuxie.crowdfunding.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.domain.TActivityCategory;
import team.wuxie.crowdfunding.service.ActivityCategoryService;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.List;

/**
 * @author WuGang
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
public class ActivityCategoryServiceImpl extends AbstractService<TActivityCategory> implements ActivityCategoryService {

    @Override
    public List<TActivityCategory> selectByEnabled(Boolean enabled) {
        TActivityCategory activityCategory = new TActivityCategory();
        activityCategory.setEnabled(enabled);
        return select(activityCategory);
    }
}
