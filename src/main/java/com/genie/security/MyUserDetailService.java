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
import com.genie.entity.Login;
import com.genie.entity.Role;

/*author:Genie
 *date:2018年2月23日
**/
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private HibernateDao hibernateDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("MyUserDetailService load user : " + username);
		Login login = (Login) hibernateDao.findByProperty(Login.class, Restrictions.eq("username", username)).get(0);
		System.out.println(login.getUsername());
		Collection<Role> roles = login.getRole();
		for(Role role : roles){
			System.out.println(role.getRoleName());
		}
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
