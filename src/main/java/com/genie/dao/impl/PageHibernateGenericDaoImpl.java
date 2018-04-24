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
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.genie.dao.DaoException;
import com.genie.dao.Page;
import com.genie.dao.PageGenericDao;
import com.genie.dao.ReflectionUtils;

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


	@Override
	public Page<T> queryPage(Page<T> page, String hql, Object... values) {
		Assert.notNull(page, "row不能为空");
		Query q = createQuery(hql, values);
		int totalCount = countByHql(hql, values);
		page.setTotalCount(totalCount);
		q.setFirstResult(page.getCurrentPage());
		q.setMaxResults(page.getPageSize());
		List<T> result = q.list();
		page.setResultList(result);
		return page;
	}

	@Override
	public Page<T> queryPage(Page<T> page, Criterion... criterions) {
		Assert.notNull(page, "row不能为空");
		
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		int totalCount = countCriteriaResult(criteria);
		page.setTotalCount(totalCount);
		criteria.setFirstResult(page.getCurrentPage()-1);
		criteria.setMaxResults(page.getPageSize());
		List<T> result = criteria.list();
		page.setResultList(result);
		return page;
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

	public int countCriteriaResult(Criteria c) {
		// TODO Auto-generated method stub
		CriteriaImpl impl = (CriteriaImpl) c;
		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List<CriteriaImpl.OrderEntry>) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
		// 执行Count查询
		int totalCount = (Integer) c.setProjection(Projections.rowCount()).uniqueResult();
		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);
		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
		return totalCount;
	}
}
