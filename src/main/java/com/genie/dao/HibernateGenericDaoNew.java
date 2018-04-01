package com.genie.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


public class HibernateGenericDaoNew<T, PK extends Serializable> implements GenericDaoOld<T, PK> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	// session工厂对象
	protected SessionFactory sessionFactory;

	// 实体类对象
	protected Class<T> entityClass;

	/**
	 * 
	 * Description : HibernateDao不带参数的构造函数
	 * 
	 * @return
	 * 
	 */
	public HibernateGenericDaoNew() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 
	 * Description : HibernateGenericDao带参数的构造函数
	 * 
	 * @param SessionFactory
	 *            Session对象
	 * @param entityClass
	 *            实体类
	 * @return
	 * 
	 */
	public HibernateGenericDaoNew(final SessionFactory sessionFactory, final Class<T> entityClass) {
		logger.debug("初始化sessionFactory");
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 获取当前session工厂对象
	public Session getSession() {
		logger.debug("getCurrentSession获取sessionFactory");
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 
	 * Description : 删除实体对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return
	 *
	 */
	public void delete(T entity) {
		logger.info("delete对象：" + entity.getClass().getName());
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		logger.info("delete entity: {}", entity.getClass().getName());
	}

	/**
	 * 
	 * Description : 获取实体对象
	 * 
	 * @param id
	 *            实体主键
	 * @return entity 实体对象
	 *
	 */
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		logger.info("根据ID" + id + "获取实体对象");
		Assert.notNull(id, "id不能为空");
		logger.info("get entity: {}", id);
		return (T) getSession().get(entityClass, id);
	}

	/**
	 * 
	 * Description : 保存实体对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return
	 *
	 */
	public Serializable save(T entity) {
		Assert.notNull(entity, "entity不能为空");
		Serializable id = getSession().save(entity);
		logger.info("save entity: {}", entity.getClass().getName());
		return id;
	}

	/**
	 * 
	 * Description : 保存或修改实体对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return
	 *
	 */
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().saveOrUpdate(entity);
		logger.info("saveOrUpdate entity: {}", entity.getClass().getName());
	}

	/**
	 * 
	 * Description : 修改实体对象
	 *
	 * @param entity
	 *            实体对象
	 * @return
	 *
	 */
	public void update(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().update(entity);
		logger.info("update entity: {}", entity.getClass().getName());
	}

	/**
	 * 
	 * Description : 根据主键删除实体
	 * 
	 * @param id
	 *            主键
	 * @return
	 *
	 */
	public void deleteById(Serializable id) {
		T entity = this.get(id);
		getSession().delete(entity);
		logger.info("delete entity: {}", id);
	}

	/**
	 * 
	 * Description : 根据SQL查询，取结果集合总数
	 * 
	 * @param sql
	 *            SQL查询语句
	 * @param values
	 *            查询值
	 * @return 结果集合总数
	 *
	 */
	public int countBySql(String sql, Object... values) {
		logger.info("countBySql", sql);
		Query query = createSQLQuery(sql, values);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	/**
	 * 根据属性查询，取结果集合中的第0个结果
	 * 
	 * @param pName
	 * @param pValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getUniqueByProperty(String pName, Object pValue) {
		logger.info("根据属性查询，取结果集合中的第0个结果getUniqueByProperty", pName);
		String hql = "select model from " + entityClass.getName() + " as model where model." + pName + " = '" + pValue
				+ "'";
		List<T> objs = getSession().createQuery(hql).list();
		if (objs != null && objs.size() != 0) {
			return objs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据多个属性查询，取结果集合中的第0个结果
	 * 
	 * @param clazz
	 * @param strs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getUniqueByNProperty(Object... strs) {
		if (strs != null && strs.length != 0 && 0 == strs.length % 2) {
			StringBuffer hql = new StringBuffer("select model from " + entityClass.getName() + " as model where ");
			for (int i = 0; i < strs.length; i += 2) {
				hql.append(" " + strs[i] + " = '" + strs[i + 1] + "' and");
			}
			int hqlLength = hql.length();
			hql.delete(hqlLength - 3, hqlLength);// 去掉最后的and
			List<T> objs = getSession().createQuery(hql.toString()).list();
			if (objs != null && objs.size() != 0) {
				return objs.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 根据HQL查询，取结果集合中的第0个结果
	 * 
	 * @param hql
	 * @return
	 */
	public T getUniqueByHql(String hql, Object... values) {
		List<T> objs = this.query(hql, values);
		if (objs != null && objs.size() != 0) {
			return objs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据SQL查询，取结果集合中的第0个结果
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getUniqueBySql(String sql, Object... values) {
		SQLQuery query = createSQLQuery(sql, values).addEntity(entityClass);
		List<T> objs = query.list();
		if (objs != null && objs.size() != 0) {
			return objs.get(0);
		} else {
			return null;
		}
	}
	////////////////////// 查询单个完毕////////////////

	/**
	 * 查询所有
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList() {
		String hql = "select model from " + entityClass.getName() + " as model ";
		List<T> list = getSession().createQuery(hql).list();
		return list;
	}

	/**
	 * 根据属性查询 全部
	 * 
	 * @param pName
	 * @param pValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByProperty(String pName, Object pValue) {
		String hql = "select model from " + entityClass.getName() + " as model where model." + pName + " = '" + pValue
				+ "'";
		return getSession().createQuery(hql).list();
	}

	/**
	 * 根据属性和条件查询 全部
	 * 
	 * @param pName
	 * @param pValue
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByProperty(String pName, Object pValue, String condition) {
		String hql = "select model from " + entityClass.getName() + " as model where model." + pName + " " + condition
				+ " '%" + pValue + "%'";
		List<T> list = getSession().createQuery(hql).list();
		return list;
	}

	/**
	 * 根据多个属性模糊查询
	 * 
	 * @param strs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByNProperty(Object... strs) {
		if (strs != null && strs.length != 0 && 0 == strs.length % 2) {
			StringBuffer hql = new StringBuffer("select model from " + entityClass.getName() + " as model where");
			for (int i = 0; i < strs.length; i += 2) {
				hql.append(" " + strs[i] + " = '" + strs[i + 1] + "' and");
			}
			int hqlLength = hql.length();
			hql.delete(hqlLength - 3, hqlLength);// 去掉最后的and
			List<T> objs = getSession().createQuery(hql.toString()).list();
			return objs;
		} else {
			return null;
		}
	}

	/**
	 * 根据HQL查询 全部
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByHql(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 根据SQL查询全部
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListBySql(String sql, Object... values) {
		SQLQuery query = createSQLQuery(sql, values).addEntity(entityClass);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	// @Cacheable(cacheName="departCache")
	public <X> List<X> query(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> query(String hql, Map<String, Object> values) {
		return createQuery(hql, values).list();
	}

	@SuppressWarnings("unchecked")
	public <X> X findUnique(String hql, Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <X> X findUnique(String hql, Map<String, Object> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
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

	// @Cacheable(cacheName="departCache")
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

	public int executeUpdate(final String hql, final Object... values) {

		return createQuery(hql, values).executeUpdate();
	}

	public int executeUpdate(final String hql, final Map<String, Object> values) {
		return createQuery(hql, values).executeUpdate();
	}

	public void initEntity(T entity) {
		Hibernate.initialize(entity);
	}

	public void initEntity(List<T> entityList) {
		for (T entity : entityList) {
			Hibernate.initialize(entity);
		}
	}

	/**
	 * 
	 * Description : 根据Criterion条件创建Criteria
	 * 
	 * @param criterions
	 * @return
	 * 
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public int countByHql(String hql, Object... values) {
		Query query = createQuery(hql, values);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public List<Map<String, Object>> queryListBysql(String queryString, Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		SQLQuery query = getSession().createSQLQuery(queryString);
		// query.setCacheable(false);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] != null && StringUtils.isNotEmpty(values[i].toString()))
					query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}
}
