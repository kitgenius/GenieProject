package com.genie.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

public interface PageGenericDao<T, PK extends Serializable> extends GenericDao<T, PK> {

	/**
	 * 
	 * Description : 按HQL分页查询
	 *
	 * @param page
	 * @param hql
	 * @param values
	 * @return
	 *
	 */
	public Page<T> queryPage(final Page<T> page, final String hql, final Object... values);

	/**
	 * 
	 * Description : HQL分页查询
	 * 
	 * @param Page
	 *            分页对象
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            map对象参数值
	 * @return
	 * 
	 */
	public Page<T> queryPage(final Page<T> page, final String hql, final Map<String, Object> values);

	/**
	 * 
	 * Description : 按Criteria分页查询
	 *
	 * @param page
	 *            分页对象
	 * @param criterions
	 *            条件
	 * @return
	 *
	 */
	public Page<T> queryPage(final Page<T> page, final Criterion... criterions);

	/**
	 * 
	 * Description : 执行count查询获得本次Hql查询所能获得的对象总数
	 *
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            查询值
	 * @return
	 *
	 *//*
	public int countHqlResult(final String hql, final Object... values);

	*//**
	 * 
	 * Description : 执行count查询获得本次Hql查询所能获得的对象总数
	 *
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            map对象查询值
	 * @return
	 *
	 *//*
	public int countHqlResult(final String hql, final Map<String, Object> values);*/

	/**
	 * 
	 * Description : 执行count查询获得本次Criteria查询所能获得的对象总数
	 *
	 * @param c
	 *            Criteria对象
	 * @return
	 *
	 *//*
	public int countCriteriaResult(final Criteria c);*/
}
