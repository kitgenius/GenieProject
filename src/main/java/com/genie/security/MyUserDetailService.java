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
		Login login = (Login) hibernateDao.findByProperty(Login.class, Restrictions.eq("username", username)).get(0);
		Collection<Role> roles = login.getRole();
		System.out.println("load user : " + username);
		Collection<GrantedAuthority> auths = null;
		if(login != null){
			auths = new ArrayList<GrantedAuthority>();
			if(roles.isEmpty() == false){
				for(Role role:roles){
					auths.add(new SimpleGrantedAuthority(role.getRoleName()));
				}
			}
		}
		
		User user = new User(username, login.getPassword(), true, true, true, true, auths);
		
		return user;
	}
	

}
