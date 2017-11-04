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

import com.genie.dao.ChildDao;
import com.genie.entity.Child;

/**
 * Child的dao实现类
 * @see com.genie.entity.Child
 * @author Genie
 */
@Repository("childDao")
public class ChildDaoImpl implements ChildDao{

	private static final Log log = LogFactory.getLog(ChildDaoImpl.class);

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

	public void persist(Child transientInstance) {
		log.debug("persisting Child instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Child instance) {
		log.debug("attaching dirty TChild instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Child instance) {
		log.debug("attaching clean Child instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Child persistentInstance) {
		log.debug("deleting Child instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Child merge(Child detachedInstance) {
		log.debug("merging Child instance");
		try {
			Child result = (Child) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Child findById(java.lang.Integer id) {
		log.debug("getting Child instance with id: " + id);
		try {
			Child instance = (Child) sessionFactory.getCurrentSession().get("com.genie.entity.Child", id);
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

	public List findByExample(Child instance) {
		log.debug("finding Child instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.genie.entity.Child").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<Child> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
