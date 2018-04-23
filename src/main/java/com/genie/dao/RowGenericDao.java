package com.genie.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

public interface RowGenericDao<T, PK extends Serializable> extends GenericDao<T, PK> {

	/**
	 * 
	 * Description : 按HQL分页查询
	 *
	 * @param row
	 * @param hql
	 * @param values
	 * @return
	 *
	 */
	public Row<T> queryPage(final Row<T> row, final String hql, final Object... values);

	/**
	 * 
	 * Description : 按Criteria分页查询
	 *
	 * @param row
	 *            分页对象
	 * @param criterions
	 *            条件
	 * @return
	 *
	 */
	public Row<T> queryPage(final Row<T> row, final Criterion... criterions);

	
}
