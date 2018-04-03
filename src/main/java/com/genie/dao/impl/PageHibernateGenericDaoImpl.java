package com.genie.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.genie.dao.DaoException;
import com.genie.dao.Page;
import com.genie.dao.PageGenericDao;

/*author:Genie
 *date:2018年4月1日
**/
public class PageHibernateGenericDaoImpl<T, PK extends Serializable> extends HibernateDaoImpl implements PageGenericDao<T, PK> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	// 实体类对象
	protected Class<T> entityClass;
	
	public PageHibernateGenericDaoImpl() {
		Type genType = getClass().getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(getClass() + "'s superclass not ParameterizedType");
			entityClass = (Class<T>) Object.class;
		}else{
			Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

			if (params[0] instanceof Class) {
				entityClass = (Class<T>) params[0];
			}
			else{
				logger.warn(getClass() + " not set the actual class on superclass generic parameter");
				entityClass = (Class<T>) Object.class;
			}
		}

		
	}

	public PageHibernateGenericDaoImpl(final SessionFactory sessionFactory, final Class<T> entityClass) {
		logger.debug("初始化sessionFactory");
		this.entityClass = entityClass;
	}
	
	@Override
	public List<T> findAll() {
		return super.findAll(entityClass.getClass());
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
	public T findById(Serializable id) {
		logger.info("根据ID" + id + "获取实体对象");
		Assert.notNull(id, "id不能为空");
		logger.info("get entity: {}", id);
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public void deleteById(Serializable id) {
		delete(findById(id));
		
	}

	@Override
	public T findUniqueByProperty(String pName, Object pValue) {
		List<T> resultList = findByProperty(pName, pValue);
		return resultList.get(0);
	}
	
	@Override
	public T findUniqueByNProperty(String[] pNames, Object[] pValues) {
		List<T> result = findByNProperty(pNames,pValues);
		return result.get(0);
	}

	@Override
	public T findUniqueByHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findUniqueBySql(String sql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByExample(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByProperty(String pName, Object pValue) {
		Criterion restriction = Restrictions.eq(pName, pValue);
		List<T> resultList = findByProperty(entityClass.getClass(), restriction);
		return resultList;
	}

	@Override
	public List<T> findByNProperty(String[] pNames,Object[] pValues) {
		if (pNames.length != pValues.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		List<Criterion> restrictionsList = new ArrayList();
		for(int i =0 ; i < pValues.length ; i++){
			restrictionsList.add(Restrictions.eqOrIsNull(pNames[i], pValues[i]));
		}
		
		List<T> resultList = findByCriterions(entityClass.getClass(), restrictionsList);
		return resultList;
	}


	@Override
	public void initEntity(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initEntity(List<T> entityList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> List<X> query(String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X> List<X> query(String hql, Map<String, Object> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X> X findUnique(String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X> X findUnique(String hql, Map<String, Object> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findUnique(Criterion... criterions) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int executeUpdate(String queryString, Object... values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String queryString, Map<String, Object> values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countBySql(String sql, Object... values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countByHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Page<T> queryPage(Page<T> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> queryPage(Page<T> page, String hql, Map<String, Object> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> queryPage(Page<T> page, Criterion... criterions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countHqlResult(String hql, Object... values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countHqlResult(String hql, Map<String, Object> values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countCriteriaResult(Criteria c) {
		// TODO Auto-generated method stub
		return 0;
	}



	
}
