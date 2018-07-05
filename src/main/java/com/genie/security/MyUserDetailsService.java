package com.genie.security;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.genie.dao.HibernateDao;
import com.genie.entity.ManagerUser;
import com.genie.entity.Role;

/**
 * author:Genie
 * date:2018年2月23日
 * 用于获取用户的元数据-用户权限，判断用户是否具备某个权限-GrantedAuthority-角色名，根据角色名进行鉴权。
 * spring security拦截到登陆请求后-由登陆验证拦截器AuthenticationProcessingFilter拦截，
 * 本类由authentication-manager调用loadUserByUsername方法获得用户名、密码和角色名集合进行认证，
 * 通过认证后，用户的权限信息-User/UserDetails保存在全局缓存SecurityContextHolder中
**/
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private HibernateDao hibernateDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("MyUserDetailService load user : " + username);
		ManagerUser login = (ManagerUser) hibernateDao.findByProperty(ManagerUser.class, Restrictions.eq("username", username)).get(0);
		System.out.println(login.getUsername());
		Collection<Role> roles = login.getRole();
		for(Role role : roles){
			System.out.println(role.getRoleName());
		}
		//GrantedAuthority是用户的权限相关信息
		Collection<GrantedAuthority> auths = null;
		if(login != null){
			auths = new ArrayList<GrantedAuthority>();
			if(roles.isEmpty() == false){
				for(Role role:roles){
					System.out.println("返回角色 ： " + role.getRoleName());
					auths.add(new SimpleGrantedAuthority(role.getRoleName()));
				}
			}
		}
		
		User user = new User(username, login.getPassword(), auths);
		
		return user;
	}
	

}
