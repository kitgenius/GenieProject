package com.genie.service;

import com.genie.entity.Login;

/*author:Genie
 *date:2017年10月10日
 *管理登陆账号信息
**/
public interface LoginManage {
	boolean addLogin(String username,String password);
	boolean deleteLogin(String username);
	Login findLogin(String username);
	boolean editPassowrd(String username,String oldPassowrd,String newPassword);
}
