package com.genie.dao.impl;
// default package
// Generated 2017-11-8 22:22:22 by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import com.genie.entity.Organ;

/**
 * Home object for domain model class Organ.
 * @see .Organ
 * @author Hibernate Tools
 */
public class OrganDaoImpl {

	private static final Log log = LogFactory.getLog(OrganDaoImpl.class);

	/*private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}*/
	
	@Autowired
	private SessionFactory sessionFactory;

	public void persist(Organ transientInstance) {
		log.debug("persisting Organ instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Organ instance) {
		log.debug("attaching dirty Organ instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Organ instance) {
		log.debug("attaching clean Organ instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Organ persistentInstance) {
		log.debug("deleting Organ instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Organ merge(Organ detachedInstance) {
		log.debug("merging Organ instance");
		try {
			Organ result = (Organ) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Organ findById(java.lang.Integer id) {
		log.debug("getting Organ instance with id: " + id);
		try {
			Organ instance = (Organ) sessionFactory.getCurrentSession().get("com.genie.entity.Organ", id);
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

	public List findByExample(Organ instance) {
		log.debug("finding Organ instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.genie.entity.Organ").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
