package com.genie.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/*author:Genie
 *date:2018年2月28日
**/
public class MyAccessDecisionManager implements AccessDecisionManager {

	
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if(configAttributes == null){
			return;
		}
		
		Iterator<ConfigAttribute> itCA = configAttributes.iterator();
		while(itCA.hasNext()){
			ConfigAttribute ca = itCA.next();
			String needRole = ((SecurityConfig)ca).getAttribute();
			System.out.println("need role : " + needRole);
			for(GrantedAuthority ga : authentication.getAuthorities()){
				System.out.println("you have role : " + ga.getAuthority());
				if(needRole.equals(ga.getAuthority())){
					System.out.println("if equal ? : " + ga.getAuthority());
					return;
				}
			}
		}
		
		throw new AccessDeniedException("no right");

	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
