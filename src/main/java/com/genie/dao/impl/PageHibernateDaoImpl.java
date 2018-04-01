package com.genie.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

import com.genie.dao.Page;
import com.genie.dao.PageGenericDao;

/*author:Genie
 *date:2018年4月1日
**/
public class PageHibernateDaoImpl<T, PK extends Serializable> extends HibernateDaoImpl implements PageGenericDao<T, PK> {

	@Override
	public T get(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getUniqueByProperty(String pName, Object pValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getUniqueByNProperty(Object... strs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getUniqueByHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getUniqueBySql(String sql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getListByProperty(String pName, Object pValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getListByProperty(String pName, Object pValue, String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getListByNProperty(Object... strs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getListByHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getListBySql(String sql, Object... values) {
		// TODO Auto-generated method stub
		return null;
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
	public List<T> queryByExample(T entity) {
		// TODO Auto-generated method stub
		return null;
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
	public Query createQuery(String queryString, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(String queryString, Map<String, Object> values) {
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
	public List<Map<String, Object>> queryListBysql(String queryString, Object... values) {
		// TODO Auto-generated method stub
		return null;
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
