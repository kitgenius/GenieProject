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
	 * Description : 获取 实体对象
	 *
	 * @param id
	 * @return
	 *
	 */
	public List<T> findAll();
	
	/**
	 * 根据主键查找
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);

	/**
	 * 根据对象查询 全部
	 * @param entity
	 * @return
	 */
	List<T> findByExample(T entity);

	/**
	 * 
	 * Description : 按Criteria查询唯一对象
	 *
	 * @param criterions
	 * @return
	 *
	 */
	public T findUnique(List restrictions);
	
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 */
	public void deleteById(Serializable id);
	
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
	 * Description : 保存 实体对象
	 *
	 * @param entity
	 *
	 */
	public Serializable save(final T entity);

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
	
}
