package com.genie.dao.impl;
// default package
// Generated 2017-10-13 22:35:14 by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.genie.dao.UserInfoDao;
import com.genie.entity.UserInfo;

/**
 * 用户信息dao，用户信息管理
 * @see com.LoginVO.faith.model.Userinfo
 * @author Genie
 */
@Repository("userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao{

	private static final Log log = LogFactory.getLog(UserInfoDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	/*private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}*/

	public void persist(UserInfo transientInstance) {
		log.debug("persisting TUserinfo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UserInfo instance) {
		log.debug("attaching dirty TUserinfo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserInfo instance) {
		log.debug("attaching clean TUserinfo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UserInfo persistentInstance) {
		log.debug("deleting TUserinfo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserInfo merge(UserInfo detachedInstance) {
		log.debug("merging TUserinfo instance");
		try {
			UserInfo result = (UserInfo) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserInfo findById(java.lang.Integer id) {
		log.debug("getting TUserinfo instance with id: " + id);
		try {
			UserInfo instance = (UserInfo) sessionFactory.getCurrentSession().get("com.genie.entity.UserInfo", id);
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

	public List findByExample(UserInfo instance) {
		log.debug("finding TUserinfo instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.genie.entity.UserInfo").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
