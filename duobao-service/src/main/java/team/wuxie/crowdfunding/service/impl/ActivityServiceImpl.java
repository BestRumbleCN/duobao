package team.wuxie.crowdfunding.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.domain.TActivity;
import team.wuxie.crowdfunding.service.ActivityService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * @author WuGang
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
public class ActivityServiceImpl extends AbstractService<TActivity> implements ActivityService {

}
