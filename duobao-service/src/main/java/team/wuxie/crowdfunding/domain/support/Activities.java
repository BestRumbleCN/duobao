package team.wuxie.crowdfunding.domain.support;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TActivity;
import team.wuxie.crowdfunding.domain.TActivityCategory;
import team.wuxie.crowdfunding.mapper.TActivityCategoryMapper;
import team.wuxie.crowdfunding.mapper.TActivityMapper;
import team.wuxie.crowdfunding.model.ActivityQuery;
import team.wuxie.crowdfunding.util.page.DtModel;
import team.wuxie.crowdfunding.util.page.Page;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Activities: 活动相关领域模型 -- 涉及活动表、活动分类表
 *
 * @author WuGang
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Activities implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private TActivityMapper activityMapper;
    private TActivityCategoryMapper activityCategoryMapper;

    private Activities() {
    }

    private static final Activities INSTANCE = new Activities();

    public static Activities getInstance() {
        return INSTANCE;
    }

    public static TActivityMapper activityMapper() {
        return INSTANCE.activityMapper;
    }

    public static TActivityCategoryMapper activityCategoryMapper() {
        return INSTANCE.activityCategoryMapper;
    }

    @Nullable
    @Contract("null -> null")
    public static TActivity selectById(Integer activityId) {
        return activityMapper().selectByPrimaryKey(activityId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TActivity selectByIdOrFail(Integer activityId) {
        checkArgument(activityId != null && activityId > 0, "activityId illegal");
        TActivity activity = selectById(activityId);
        activityNotFound(activity);
        return activity;
    }

    public static Page<TActivity> findActivityPage(String table, ActivityQuery query) {
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<TActivity> list = activityMapper().selectAllByQuery(query);
        PageInfo<TActivity> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }

    /**
     * 查找所有分类
     *
     * @param enabled 可为空，为空时查找全部
     * @return
     */
    public static List<TActivityCategory> findAllCategories(@Nullable Boolean enabled) {
        if (enabled == null) {
            return activityCategoryMapper().selectAll();
        } else {
            TActivityCategory activityCategory = new TActivityCategory();
            activityCategory.setEnabled(enabled);
            return activityCategoryMapper().select(activityCategory);
        }
    }

    @Nullable
    @Contract("null -> null")
    public static TActivityCategory selectCategoryById(Integer categoryId) {
        return activityCategoryMapper().selectByPrimaryKey(categoryId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TActivityCategory selectCategoryByIdOrFail(Integer categoryId) {
        checkArgument(categoryId != null && categoryId > 0, "categoryId illegal");
        TActivityCategory activityCategory = selectCategoryById(categoryId);
        activityCategoryNotFound(activityCategory);
        return activityCategory;
    }

    public static Page<TActivityCategory> findActivityCategoryPage(String table) {
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<TActivityCategory> list = activityCategoryMapper().selectAll();
        PageInfo<TActivityCategory> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }

    @Contract("null -> fail")
    private static void activityNotFound(TActivity activity) throws EntityNotFoundException {
        if (activity == null) throw new EntityNotFoundException("activity.not_found");
    }

    @Contract("null -> fail")
    private static void activityCategoryNotFound(TActivityCategory activityCategory) throws EntityNotFoundException {
        if (activityCategory == null) throw new EntityNotFoundException("activityCategory.not_found");
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        activityMapper = applicationContext.getBean(TActivityMapper.class);
        activityCategoryMapper = applicationContext.getBean(TActivityCategoryMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeInjector();
    }

    public void setActivityMapper(TActivityMapper activityMapper) {
        checkNotNull(activityMapper, "activityMapper");
        this.activityMapper = activityMapper;
    }

    public void setActivityCategoryMapper(TActivityCategoryMapper activityCategoryMapper) {
        checkNotNull(activityCategoryMapper, "activityCategoryMapper");
        this.activityCategoryMapper = activityCategoryMapper;
    }
}
