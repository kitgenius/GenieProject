package com.genie.dao.impl;
// Generated 2017-6-17 22:38:14 by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genie.entity.TEvent;

/**
 * Home object for domain model class TEvent.
 * @see com.genie.dao.impl.TEvent
 * @author Hibernate Tools
 */
@Repository("tEventDao")
public class TEventHome {

	private static final Log log = LogFactory.getLog(TEventHome.class);

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
		
	public void persist(TEvent transientInstance) {
		log.debug("persisting TEvent instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TEvent instance) {
		log.debug("attaching dirty TEvent instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TEvent instance) {
		log.debug("attaching clean TEvent instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TEvent persistentInstance) {
		log.debug("deleting TEvent instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TEvent merge(TEvent detachedInstance) {
		log.debug("merging TEvent instance");
		try {
			TEvent result = (TEvent) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TEvent findById(java.lang.Integer id) {
		log.debug("getting TEvent instance with id: " + id);
		try {
			TEvent instance = (TEvent) sessionFactory.getCurrentSession().get("com.genie.model.TEvent", id);
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

	public List findByExample(TEvent instance) {
		log.debug("finding TEvent instance by example");
		System.out.println("instance's code is :" + instance.getCode());
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.genie.model.TEvent")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			System.out.println("results's len : " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
