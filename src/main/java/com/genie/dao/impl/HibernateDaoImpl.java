/***********************************************************************
 * Module:  HibernateDaoImpl.java
 * Author:  Administrator
 * Purpose: Defines implementation class of HibernateDao interface
 ***********************************************************************/

package com.genie.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.genie.dao.DaoException;
import com.genie.dao.HibernateDao;

/**
 * Class that implements <Code>HibernateDao</Code> interface.
 *
 */
@Repository("hibernateDao")
public class HibernateDaoImpl implements HibernateDao {

	@Autowired
	private SessionFactory sessionFactory;

	//缓存标识
	private boolean cacheQueries = false;
	//命名缓存区域
	private String queryCacheRegion;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/*
	 * Empty constructor
	 */
	public HibernateDaoImpl() {
		super();
	}

	/*
	 * 获取session
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/*
	 * 更新
	 */
	public void update(Object persistentObject) throws DaoException {
		try {
			Session session = getSession();
			session.update(persistentObject);
		} catch (HibernateException ex) {
			logger.error("Fail to update", ex);
			throw new DaoException("Fail to update", ex);
		}
	}

	/*
	 * 删除
	 */
	public void delete(Object persistentObject) throws DaoException {
		try {
			Session session = getSession();
			session.delete(persistentObject);
		} catch (HibernateException ex) {
			logger.error("Fail to delete", ex);
			throw new DaoException("Fail tlo delete", ex);
		}
	}

	/*
	 * 根据某个持久化对象查询
	 */
	public List findByExample(Class clazz, Object persistentObject) throws DaoException {
		List objs = new ArrayList();

		try {
			Session session = this.getSession();
			objs = session.createCriteria(clazz).add(Example.create(persistentObject)).list();
		} catch (HibernateException ex) {
			logger.error("Fail to find all  objects", ex);
			throw new DaoException("Fail to find all  objects", ex);
		}
		return objs;
	}

	/*
	 * 查询所有
	 */
	public List findAll(Class clazz) throws DaoException {
		List objs = new ArrayList();

		try {
			Session session = this.getSession();
			objs = session.createCriteria(clazz).list();
		} catch (HibernateException ex) {
			logger.error("Fail to find all  objects", ex);
			throw new DaoException("Fail to find all  objects", ex);
		}
		return objs;
	}
	
	/*
	 * 根据实体类加载所有数据，返回持久化实例
	 */
	public List findAll(final Class entityClass, final int firstResult, final int maxResult) throws DaoException {
		Session session = this.getSession();
		try {
			Criteria criteria = session.createCriteria(entityClass).setFirstResult(firstResult)
					.setMaxResults(maxResult);
			return criteria.list();
		} catch (HibernateException ex) {
			logger.error("Fail to load all ", ex);
			throw new DaoException("Fail to load all", ex);
		} 
	}


	/*
	 * 根据持久类单个条件查找
	 */
	public List findByProperty(Class clazz, Criterion restriction) throws DaoException {
		List objs = new ArrayList();

		try {
			Session session = this.getSession();
			objs = session.createCriteria(clazz).add(restriction).list();
		} catch (HibernateException ex) {
			logger.error("Fail to find objects by property", ex);
			throw new DaoException("Fail to find objects by property", ex);
		}
		return objs;
	}

	/*
	 * 根据持久类多个条件查找，多个条件and
	 */
	public List findByCriterions(Class clazz, List restrictions) throws DaoException {
		List objs = new ArrayList();

		try {
			Session session = this.getSession();
			Criteria criteria = session.createCriteria(clazz);
			Iterator it = restrictions.iterator();
			while (it.hasNext())
				criteria.add((Criterion) it.next());
			objs = criteria.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find objects by criterions", ex);
			throw new DaoException("Fail to find objects by criterions", ex);
		}
		return objs;
	}

	/**
	 * 根据多个条件查询，多个条件and。指定从第几条数据开始返回和返回数据的条数，第一条设置firstResult=0
	 * @param clazz 持久化类
	 * @param restrictions 条件
	 * @param firstResult 从第几条数据返回
	 * @param maxResult 返回数据最大限制
	 * @return 查询结果列表
	 * @throws DaoException
	 */
	public List findByCriterions(Class clazz, List restrictions, int firstResult, int maxResult) throws DaoException {
		List objs = new ArrayList();

		try {
			Session session = this.getSession();
			Criteria criteria = session.createCriteria(clazz).setFirstResult(firstResult).setMaxResults(maxResult);
			Iterator it = restrictions.iterator();
			while (it.hasNext())
				criteria.add((Criterion) it.next());
			objs = criteria.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find objects by criterions", ex);
			throw new DaoException("Fail to find objects by criterions", ex);
		}
		return objs;
	}

	/*
	 * 根据sql语句查询
	 */
	public List findBySQLQuery(String sqlQuery, String aliasName, Class clazz) throws DaoException {
		List result = new ArrayList();
		try {
			Session session = this.getSession();
			result = session.createSQLQuery(sqlQuery).addEntity(aliasName, clazz).list();
		} catch (HibernateException ex) {
			logger.error("Fail to execute query", ex);
			throw new DaoException("Fail to execute query", ex);
		}
		return result;
	}

	/*
	 * 根据hql语句查询
	 */
	public List findByHQLQuery(String hqlQuery) throws DaoException {
		List result = new ArrayList();
		try {
			Session session = this.getSession();
			result = session.createQuery(hqlQuery).list();
		} catch (HibernateException ex) {
			logger.error("Fail to execute query", ex);
			throw new DaoException("Fail to execute query", ex);
		}
		return result;
	}

	/*
	 * hql设置1个参数并赋值条件查询数据
	 */
	public List findByNamedParam(String queryString, String paramName, Object value) throws DaoException {
		return findByNamedParam(queryString, new String[] { paramName }, new Object[] { value });
	}

	/*
	 * hql设置1个参数并赋值条件查询数据，指定从第几条数据开始返回和返回数据的条数，第一条设置firstResult=0
	 */
	public List findByNamedParam(String queryString, String paramName, Object value, int firstResult, int maxResult)
			throws DaoException {
		return findByNamedParam(queryString, new String[] { paramName }, new Object[] { value }, firstResult,
				maxResult);
	}

	/*
	 * hql设置多个参数并赋值条件查询数据
	 */
	public List findByNamedParam(final String queryString, final String[] paramNames, final Object[] values)
			throws DaoException {
		try {
			if (paramNames.length != values.length) {
				throw new IllegalArgumentException("Length of paramNames array must match length of values array");
			}
			Session session = this.getSession();
			Query queryObject = session.createQuery(queryString);

			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
				}
			}

			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find", ex);
			throw new DaoException("Fail to find", ex);
		}
	}

	/*
	 * hql设置多个参数并赋值条件查询数据，指定从第几条数据开始返回和返回数据的条数，第一条设置firstResult=0
	 */
	public List findByNamedParam(final String queryString, final String[] paramNames, final Object[] values,
			final int firstResult, final int maxResult) throws DaoException {
		if (paramNames.length != values.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		try {
			Session session = this.getSession();
			Query queryObject = session.createQuery(queryString);
			queryObject.setFirstResult(firstResult);
			queryObject.setMaxResults(maxResult);
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
				}
			}
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by named param");
			throw new DaoException("Fail to find by named param", ex);
		}
	}

	/*
	 * hql设置一个或多个参数并赋值条件查询数据，传入pojo进行查询
	 */
	public List findByValueBean(final String queryString, final Object valueBean) throws DaoException {
		Session session = this.getSession();
		try {
			Query queryObject = session.createQuery(queryString);
			prepareQuery(queryObject);
			queryObject.setProperties(valueBean);
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by valueBean", ex);
			throw new DaoException("Fail to find by valueBeam", ex);
		}
	}

	/*
	 * 根据预先定义的查询语句
	 */
	public List findByNamedQuery(String queryName) throws DaoException {
		return findByNamedQuery(queryName, (Object[]) null);
	}

	/*
	 * 根据预先定义的查询语句，并设置一个查询条件进行查询
	 */
	public List findByNamedQuery(String queryName, Object value) throws DaoException {
		return findByNamedQuery(queryName, new Object[] { value });
	}

	/*
	 * 根据预先定义的查询语句，并设置多个查询条件进行查询
	 */
	public List findByNamedQuery(final String queryName, final Object[] values) throws DaoException {
		Session session = this.getSession();
		try {
			Query queryObject = session.getNamedQuery(queryName);
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by Named query", ex);
			throw new DaoException("Fail to find by Named query", ex);
		}
	}

	/*
	 * 根据预先定义的查询语句，并设置1个查询条件进行查询
	 */
	public List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value) throws DaoException {
		return findByNamedQueryAndNamedParam(queryName, new String[] { paramName }, new Object[] { value });
	}

	/*
	 * 根据预先定义的查询语句，并设置多个查询条件进行查询，传入参数名和值
	 */
	public List findByNamedQueryAndNamedParam(final String queryName, final String[] paramNames, final Object[] values)
			throws DaoException {
		if (paramNames != null && values != null && paramNames.length != values.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		Session session = this.getSession();
		try {
			Query queryObject = session.getNamedQuery(queryName);
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
				}
			}
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by Named query and Named Param", ex);
			throw new DaoException("Fail to find by Named query and Named Param", ex);
		}
	}

	/*
	 * 根据预先定义的查询语句，设置一个或多个参数并赋值条件查询数据，一个参数传入pojo，多个参数则传入参数名-值的map。
	 */
	public List findByNamedQueryAndValueBean(final String queryName, final Object valueBean) throws DaoException {
		Session session = this.getSession();
		try {
			Query queryObject = session.getNamedQuery(queryName);
			prepareQuery(queryObject);
			queryObject.setProperties(valueBean);
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by Named query and value bean", ex);
			throw new DaoException("Fail to find by Named query and value bean", ex);
		}
	}

	/*
	 * 设置缓存
	 */
	protected void prepareQuery(Query queryObject) {
		if (isCacheQueries()) {
			queryObject.setCacheable(true);
			if (getQueryCacheRegion() != null) {
				queryObject.setCacheRegion(getQueryCacheRegion());
			}
		}
	}

	/**
	 * 设置查询参数
	 *
	 * @param queryObject query对象
	 * @param paramName hql串的参数名
	 * @param value hql串的参数值
	 * @throws HibernateException
	 */
	protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)
			throws HibernateException {
		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection) value);
		} else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		} else {
			queryObject.setParameter(paramName, value);
		}
	}

	/*
	 * 保存持久实例
	 */
	public Serializable save(Object persistentObject) throws DaoException {
		try {
			Session session = this.getSession();
			Serializable id = session.save(persistentObject);
			return id;
		} catch (HibernateException ex) {
			logger.error("Fail to save persistentObject", ex);
			throw new DaoException("Fail to save persistentObject", ex);
		}
	}

	/*
	 * 保存或更新持久实例
	 */
	public void saveOrUpdate(Object persistentObject) throws DaoException {
		try {
			Session session = this.getSession();
			session.saveOrUpdate(persistentObject);
		} catch (HibernateException ex) {
			logger.error("Fail to save or update persistentObject", ex);
			throw new DaoException("Fail to save or update persistentObject", ex);
		}
	}

	/*
	 * 根据hql查询，指定从第几条数据开始返回和返回数据的条数，第一条设置firstResult=0
	 */
	public List find(String queryString, int firstResult, int maxResult) throws DaoException {
		return find(queryString, (Object[]) null, firstResult, maxResult);
	}

	/*
	 * 根据hql查询
	 */
	public List find(String queryString) throws DaoException {
		return find(queryString, (Object[]) null);
	}

	/*
	 * 根据hql查询，设置1个查询条件，传入值进行查询
	 */
	public List find(String queryString, Object value) throws DaoException {
		return find(queryString, new Object[] { value });
	}

	/*
	 * 根据hql查询，设置多个查询条件，传入值进行查询
	 */
	public List find(final String queryString, final Object[] values) throws DaoException {
		Session session = this.getSession();
		try {
			Query queryObject = session.createQuery(queryString);
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by query string", ex);
			throw new DaoException("Fail to find by query string", ex);
		}
	}

	/*
	 * 根据hql查询，设置多个查询条件，传入值进行查询，指定从第几条数据开始返回和返回数据的条数，第一条设置firstResult=0
	 */
	public List find(final String queryString, final Object[] values, final int firstResult, final int maxResult)
			throws DaoException {
		Session session = this.getSession();
		try {
			Query queryObject = session.createQuery(queryString).setFirstResult(firstResult).setMaxResults(maxResult);
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by query string", ex);
			throw new DaoException("Fail to find by query string", ex);
		} 
	}

	public Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		query.setCacheable(true);

		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] != null && StringUtils.isNotEmpty(values[i].toString()))
					query.setParameter(i, values[i]);
			}
		}

		return query;
	}

	public Query createQuery(String queryString, Map<String, Object> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		query.setCacheable(true);

		if (values != null) {
			query.setProperties(values);
		}

		return query;
	}
	
	public SQLQuery createSQLQuery(String queryString, Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		SQLQuery query = getSession().createSQLQuery(queryString);
		query.setCacheable(true);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] != null && StringUtils.isNotEmpty(values[i].toString()))
					query.setParameter(i, values[i]);
			}
		}

		return query;
	}
	
	public int countBySql(String sql, Object... values) {
		logger.info("countBySql", sql);
		Query query = createSQLQuery(sql, values);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	
	public int countByHql(String hql, Object... values) {
		Query query = createQuery(hql, values);
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	public int executeUpdate(String hql, Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	
	public int executeUpdate(String hql, Map<String, Object> values) {
		return createQuery(hql, values).executeUpdate();
	}
	
	
	
	public String getQueryCacheRegion() {
		return queryCacheRegion;
	}

	public void setQueryCacheRegion(String queryCacheRegion) {
		this.queryCacheRegion = queryCacheRegion;
	}

	public boolean isCacheQueries(){
		return cacheQueries;
	}

}