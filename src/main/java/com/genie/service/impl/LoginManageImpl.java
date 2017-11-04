package com.genie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genie.dao.LoginDao;
import com.genie.entity.Login;
import com.genie.service.LoginManage;

/*author:Genie
 *date:2017年10月10日
**/
@Service("loginManage")
public class LoginManageImpl implements LoginManage {

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public boolean addLogin(String username, String password) {
		Login loginEntity = new Login();
		loginEntity.setUsername(username);
		if(loginDao.findByExample(loginEntity).isEmpty()){
			loginEntity.setPassword(password);
			loginDao.attachDirty(loginEntity);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteLogin(String username) {
		Login loginEntity = new Login();
		loginEntity.setUsername(username);
		loginDao.delete(loginDao.findByExample(loginEntity).get(0));
		return true;
	}

	@Override
	public Login findLogin(String username) {
		Login loginEntity = new Login();
		loginEntity.setUsername(username);
		List<Login> loginEntityList = loginDao.findByExample(loginEntity);
		if(loginEntityList.isEmpty()){
			return null;
		}else{
			return loginEntityList.get(0);
		}
	}

	@Override
	public boolean editPassowrd(String username, String oldPassowrd, String newPassword) {
		Login loginEntity = new Login(username, oldPassowrd);
		List<Login> loginEntityList = loginDao.findByExample(loginEntity);
		Login loginEntityEdit = loginEntityList.get(0);
		loginEntityEdit.setPassword(newPassword);
		loginDao.attachDirty(loginEntityEdit);
		return true;
	}

}
