package team.wuxie.crowdfunding.util.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

import java.util.List;

/**
 * AbstractService
 * Created by wushige on 4/19/2016 0019.
 */
@Service
@Transactional(readOnly = true)
public abstract class AbstractService<T> implements BaseService<T> {

    /**
     * 默认的分页大小
     */
    private final static int PAGE_SIZE = 10;

    @Autowired
    BaseMapper<T> mapper;

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param id
     * @return
     */
    @Override
    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public int selectCount(T entity) {
        return mapper.selectCount(entity);
    }

    /**
     * 查询全部结果，select(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public List<T> select(T entity) {
        return mapper.select(entity);
    }

    /**
     * 根据Condition条件进行查询
     *
     * @param condition
     * @return
     */
    @Override
    public List selectByCondition(Object condition) {
        return mapper.selectByCondition(condition);
    }

    /**
     * 根据Condition条件进行查询总数
     *
     * @param condition
     * @return
     */
    @Override
    public int selectCountByCondition(Object condition) {
        return mapper.selectCountByCondition(condition);
    }

    /**
     * 分页查询
     *
     * @param pageNum 请求页码
     * @param orderBy 排序，例如：create_time desc
     * @param entity  可为null
     * @return
     */
    @Override
    public PageInfo<T> selectPage(int pageNum, String orderBy, T entity) {
        if (Strings.isNullOrEmpty(orderBy)) orderBy = "create_time desc";
        PageHelper.startPage(pageNum, PAGE_SIZE, orderBy);
        List<T> list = select(entity);
        return new PageInfo<>(list);
    }

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int insert(T entity) {
        return mapper.insert(entity);
    }

    /**
     * 批量保存实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param entityList
     * @return
     */
    @Override
    @Transactional
    public int insertList(List<T> entityList) {
        return mapper.insertList(entityList);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public boolean insertSelective(T entity) {
        return mapper.insertSelective(entity) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int update(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public boolean updateSelective(T entity) {
        return mapper.updateByPrimaryKeySelective(entity) > 0;
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int delete(T entity) {
        return mapper.delete(entity);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int deleteById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }
}
