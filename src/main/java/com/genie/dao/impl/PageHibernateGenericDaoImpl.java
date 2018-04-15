package com.genie.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
	
<<<<<<< HEAD
=======
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
>>>>>>> branch 'test2.1' of https://github.com/kitgenius/GenieProject.git
	public void initEntity(T entity) {
		// TODO Auto-generated method stub
		
	}

	public void initEntity(List<T> entityList) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<T> findAll() {
		return super.findAll(entityClass.getClass());
	}
	
	@Override
	public T findById(Serializable id) {
		logger.info("根据ID" + id + "获取实体对象 " + entityClass.getName());
		Assert.notNull(id, "id不能为空");
		logger.info("get entity: {}", id);
		return (T) getSession().get(entityClass, id);
	}
	
	@Override
	public List<T> findByExample(T entity) {
		// TODO Auto-generated method stub
		return findByExample(entity.getClass(),entity);
	}
	
	@Override
	public T findUnique(List restrictions) {
		return (T) findByCriterions(restrictions).get(0);
	}

	@Override
	public void deleteById(Serializable id) {
		logger.info("根据ID" + id + "删除实体对象 " + entityClass.getName());
		T entity = (T) getSession().get(entityClass, id);
		getSession().delete(entity);
		logger.info("delete entity: {}", id);
	}

<<<<<<< HEAD
=======
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
>>>>>>> branch 'test2.1' of https://github.com/kitgenius/GenieProject.git

	@Override
	public Page<T> queryPage(Page<T> page, String hql, Object... values) {
		
	}

	@Override
	public Page<T> queryPage(Page<T> page, String hql, Map<String, Object> values) {
		
	}

	@Override
	public Page<T> queryPage(Page<T> page, Criterion... criterions) {
		
	}


	/**
	 * 根据一个条件查询
	 * @param restriction 条件
	 * @return
	 */
	public List findByProperty(Criterion restriction){
		return findByProperty(entityClass,restriction);
	}
	
	/**
	 * 根据多个条件查询
	 * @param restrictions 条件list
	 * @return
	 */
	public List findByCriterions(List restrictions) {
		return findByCriterions(entityClass,restrictions);
	}
<<<<<<< HEAD
	
	/**
	 * 根据多个条件查询，多个条件and。指定从第几条数据开始返回和返回数据的条数，第一条设置firstResult=0
	 * @param restrictions 条件
	 * @param firstResult 从第几条数据返回
	 * @param maxResult 返回数据最大限制
	 * @return 查询结果列表
	 */
	public List findByCriterions(List restrictions, int firstResult, int maxResult){
		return findByCriterions(entityClass, restrictions,firstResult, maxResult);
	}
=======



>>>>>>> branch 'test2.1' of https://github.com/kitgenius/GenieProject.git
	
}
