package com.genie.dao;

import com.genie.entity.Login;

/*author:Genie
 *date:2017年4月17日
**/
public interface LoginDao extends GenericDao<Login, Integer> {
	//判断账号密码匹配是否正确，正确则返回true，错误则返回false
	boolean loginVali(String username,String password);
}
