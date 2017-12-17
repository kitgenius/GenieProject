/***********************************************************************
 * Module:  HibernateDao.java
 * Author:  Administrator
 * Purpose: Defines common interface for Hibernate access
 ***********************************************************************/
 
package com.genie.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

/**
 * Interface that defines common methods for Hibernate access.
 *
 */
public interface HibernateDao extends Dao {

   public SessionFactory getSessionFactory();

   public void setSessionFactory(SessionFactory sessions);

   public Session setSession(Session session);

   public Session getSession(boolean openSession);
   
   public Session getSession();

   public void update(Object persistentObject) throws DaoException;

   public void delete(Object persistentObject) throws DaoException;
   
   public List queryByExample(Class clazz, Object persistentObject) throws DaoException;
   
   public List findAll(Class clazz) throws DaoException;

   public List findByProperty(Class clazz, Criterion restriction) throws DaoException;

   public List findByCriterions(Class clazz, List restrictions) throws DaoException;

   public List findBySQLQuery(String sqlQuery, String aliasName, Class clazz) throws DaoException;

   public List findByHQLQuery(String hqlQuery) throws DaoException;

   public List findByNamedParam(String queryString, String paramName, Object value) throws DaoException;

   public List findByNamedParam(String queryString, String paramName, Object value, int firstResult, int maxResult) throws DaoException;

   public List findByNamedParam(final String queryString, final String[] paramNames, final Object[] values) throws DaoException;

   public List findByNamedParam(final String queryString, final String[] paramNames, final Object[] values, final int firstResult, final int maxResult) throws DaoException;

   public List findByValueBean(final String queryString, final Object valueBean) throws DaoException;

   public List findByNamedQuery(String queryName) throws DaoException;

   public List findByNamedQuery(String queryName, Object value) throws DaoException;

   public List findByNamedQuery(final String queryName, final Object[] values) throws DaoException;

   public abstract List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value) throws DaoException;

   public List findByNamedQueryAndNamedParam(final String queryName, final String[] paramNames, final Object[] values) throws DaoException;

   public List findByNamedQueryAndValueBean(final String queryName, final Object valueBean) throws DaoException;

   public boolean isCacheQueries();

   public String getQueryCacheRegion();

   public Serializable save(Object persistentObject) throws DaoException;

   public void saveOrUpdate(Object persistentObject) throws DaoException;

   public List find(String queryString, int firstResult, int maxResult) throws DaoException;

   public List find(String queryString) throws DaoException;

   public List find(String queryString, Object value) throws DaoException;

   public List find(final String queryString, final Object[] values) throws DaoException;

   public List find(final String queryString, final Object[] values, final int firstResult, final int maxResult) throws DaoException;

   public List loadAll(final Class entityClass) throws DaoException;

   public List loadAll(final Class entityClass, final int firstResult, final int maxResult) throws DaoException;
}