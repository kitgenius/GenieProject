package com.genie.dao.impl;
// default package
// Generated 2017-10-17 22:07:30 by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genie.dao.ParentDao;
import com.genie.entity.Parent;

/**
 * Home object for domain model class Parent.
 * @see .Parent
 * @author Hibernate Tools
 */
@Repository("parentDao")
public class ParentDaoImpl implements ParentDao{

	private static final Log log = LogFactory.getLog(ParentDaoImpl.class);

	/*private final SessionFactory sessionFactory = getSessionFactory();*/

	/*protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}*/

	@Autowired
	private SessionFactory sessionFactory;
	
	public void persist(Parent transientInstance) {
		log.debug("persisting Parent instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Parent instance) {
		log.debug("attaching dirty Parent instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Parent instance) {
		log.debug("attaching clean Parent instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Parent persistentInstance) {
		log.debug("deleting Parent instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Parent merge(Parent detachedInstance) {
		log.debug("merging Parent instance");
		try {
			Parent result = (Parent) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Parent findById(java.lang.Integer id) {
		log.debug("getting Parent instance with id: " + id);
		try {
			Parent instance = (Parent) sessionFactory.getCurrentSession().get("com.genie.entity.Parent", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Parent instance) {
		log.debug("finding Parent instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.genie.entity.Parent").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<Parent> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
