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

	
}
