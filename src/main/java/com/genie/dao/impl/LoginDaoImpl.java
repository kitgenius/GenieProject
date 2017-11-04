package com.genie.dao.impl;
// Generated 2017-4-11 21:59:48 by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genie.dao.LoginDao;
import com.genie.entity.Login;;

/**
 * 登陆信息dao，账号密码管理
 * @see com.LoginVO.faith.model.Login
 * @author Genie
 */
@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

	private static final Log log = LogFactory.getLog(LoginDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/*protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}*/

	public void persist(Login transientInstance) {
		log.debug("persisting TLogin instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Login instance) {
		log.debug("attaching dirty TLogin instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Login instance) {
		log.debug("attaching clean TLogin instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Login persistentInstance) {
		log.debug("deleting TLogin instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Login merge(Login detachedInstance) {
		log.debug("merging TLogin instance");
		try {
			Login result = (Login) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Login findById(java.lang.Integer id) {
		log.debug("getting TLogin instance with id: " + id);
		try {
			Login instance = (Login) sessionFactory.getCurrentSession().get("com.genie.entity.Login", id);
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

	public List findByExample(Login instance) {
		log.debug("finding TLogin instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.genie.entity.Login")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<Login> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean loginVali(String username, String password) {
		Login instance = new Login();
		instance.setUsername(username);
		instance.setPassword(password);
		if(findByExample(instance).isEmpty()){
			return false;
		}else{
			return true;
		}
	}
}
