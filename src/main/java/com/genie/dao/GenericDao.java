package com.genie.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
//hibernate查询类
import org.hibernate.Query;
//hibernate查询条件类
import org.hibernate.criterion.Criterion;

public interface GenericDao<T, PK extends Serializable> {

	/**
	 * 
	 * Description : 保存 实体对象
	 *
	 * @param entity
	 *
	 */
	public Serializable save(final T entity);

	/**
	 * 
	 * Description : 删除 实体对象
	 *
	 * @param entity
	 *
	 */
	public void delete(final T entity);

	/**
	 * 
	 * Description : 获取 实体对象
	 *
	 * @param id
	 * @return
	 *
	 */
	public List<T> findAll();

	/**
	 * 
	 * Description : 更新 实体对象
	 *
	 * @param entity
	 *
	 */
	public void update(final T entity);

	/**
	 * 
	 * Description : 保存或更新 实体对象
	 *
	 * @param entity
	 *
	 */
	public void saveOrUpdate(final T entity);

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 */
	public void deleteById(Serializable id);
	
	public T findById(Serializable id);

	/**
	 * 根据属性查询，取结果集合中的第0个结果
	 * 
	 * @param pName
	 * @param pValue
	 * @return
	 */
	public T findUniqueByProperty(String pName, Object pValue);

	/**
	 * 根据多个属性查询，取结果集合中的第0个结果
	 * 
	 * @param clazz
	 * @param strs
	 * @return
	 */
	public T findUniqueByNProperty(String[] pName, Object[] pValue);

	/**
	 * 根据HQL查询，取结果集合中的第0个结果
	 * 
	 * @param hql
	 * @return
	 */
	public T findUniqueByHql(String hql, Object... values);

	/**
	 * 根据SQL查询，取结果集合中的第0个结果
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 */
	public T findUniqueBySql(String sql, Object... values);

	/**
	 * 根据对象查询 全部
	 * @param entity
	 * @return
	 */
	List<T> findByExample(T entity);
	
	/**
	 * 根据属性查询 全部
	 * 
	 * @param pName
	 * @param pValue
	 * @return
	 */
	public List<T> findByProperty(String pName, Object pValue);


	/**
	 * 根据多个属性模糊查询
	 * 
	 * @param strs
	 * @return
	 */
	public List<T> findByNProperty(String[] pNames,Object[] pValues);


	/**
	 * 
	 * Description : 初始化对象. 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化.
	 * 只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性. 如需初始化关联属性,可实现新的函数,执行:
	 * 
	 * @param entity
	 *
	 */
	public void initEntity(T entity);

	/**
	 * 
	 * Description : 初始化对象
	 *
	 * @param entityList
	 *
	 */
	public void initEntity(List<T> entityList);

	
	
	/**
	 * 
	 * Description : 按HQL查询对象列表
	 *
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 *
	 */
	public <X> List<X> query(final String hql, final Object... values);

	/**
	 * 
	 * Description : 按HQL查询对象列表
	 *
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 *
	 */
	public <X> List<X> query(final String hql, final Map<String, Object> values);

	/**
	 * 
	 * Description : 按HQL查询唯一对象
	 *
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 *
	 */
	public <X> X findUnique(final String hql, final Object... values);

	/**
	 * 
	 * Description : 按HQL查询唯一对象
	 *
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 *
	 */
	public <X> X findUnique(final String hql, final Map<String, Object> values);

	/**
	 * 
	 * Description : 按Criteria查询唯一对象
	 *
	 * @param criterions
	 * @return
	 *
	 */
	public T findUnique(final Criterion... criterions);


	/**
	 * 
	 * Description : 根据查询HQL与参数列表 执行
	 *
	 * @param queryString
	 * @param values
	 * @return
	 *
	 */
	public int executeUpdate(final String queryString, final Object... values);

	/**
	 * 
	 * Description : 根据查询HQL与参数列表 执行
	 *
	 * @param queryString
	 * @param values
	 * @return
	 *
	 */
	public int executeUpdate(final String queryString, final Map<String, Object> values);

	/**
	 * 
	 * Description : 根据查询SQL与参数列表查询返回count（*）数量
	 *
	 * @param queryString
	 * @param values
	 * @return
	 *
	 */
	public int countBySql(String sql, Object... values);

	/**
	 * 
	 * Description : 根据查询HQL与参数列表查询返回count（*）数量
	 *
	 * @param queryString
	 * @param values
	 * @return
	 *
	 */
	public int countByHql(String hql, Object... values);

	public List findByCriterions(Class clazz, List restrictions) throws DaoException;
	   
	public List findByCriterions(Class clazz, List restrictions , int firstResult, int maxResult) throws DaoException;

}
