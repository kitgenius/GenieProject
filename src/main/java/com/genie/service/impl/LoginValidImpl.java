package com.genie.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genie.dao.LoginDao;
import com.genie.entity.Login;
import com.genie.service.LoginValid;

/**
 * author:Genie
 * date:2017年5月1日
 */

@Service("loginValid")
public class LoginValidImpl implements LoginValid {

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public boolean loginValid(String username, String password) {
		Login instance = new Login();
		instance.setUsername(username);
		instance.setPassword(password);
		
		if(loginDao.queryByExample(Login.class,instance).isEmpty() == false){
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			return true;
		}else{
			return false;
		}
	}

}
