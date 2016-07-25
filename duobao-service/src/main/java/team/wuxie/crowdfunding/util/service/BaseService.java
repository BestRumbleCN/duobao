package team.wuxie.crowdfunding.util.service;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用Service
 * Created by wushige on 4/19/2016 0019.
 */
@Service
public interface BaseService<T> {
    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * @param id
     * @return
     */
    T selectById(Object id);

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     * @param entity
     * @return
     */
    int selectCount(T entity);

    /**
     * 查询全部结果，select(null)方法能达到同样的效果
     * @return
     */
    List<T> selectAll();

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     * @param entity
     * @return
     */
    List<T> select(T entity);

    /**
     * 根据Condition条件进行查询
     * @param condition
     * @return
     */
    List selectByCondition(Object condition);

    /**
     * 根据Condition条件进行查询总数
     * @param condition
     * @return
     */
    int selectCountByCondition(Object condition);

    /**
     * 分页查询
     * @param pageNum
     * @param orderBy
     * @param entity
     * @return
     */
    PageInfo<T> selectPage(int pageNum, String orderBy, T entity);

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 保存一个实体list，null的属性也会保存，不会使用数据库默认值
     * @param entityList
     * @return
     */
    int insertList(List<T> entityList);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     * @param entity
     * @return
     */
    boolean insertSelective(T entity);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 根据主键更新属性不为null的值
     * @param entity
     * @return
     */
    boolean updateSelective(T entity);

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     * @param entity
     * @return
     */
    int delete(T entity);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * @param id
     * @return
     */
    int deleteById(Object id);
}
