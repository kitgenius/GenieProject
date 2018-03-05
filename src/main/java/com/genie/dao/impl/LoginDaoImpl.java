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
public class LoginDaoImpl extends HibernateDaoImpl implements LoginDao {

	private static final Log log = LogFactory.getLog(LoginDaoImpl.class);

	

	
}
