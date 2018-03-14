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
			//return;
			System.out.println("资源没有配置权限");
			throw new AccessDeniedException("resource did not config auth");
		}
		
		Iterator<ConfigAttribute> itCA = configAttributes.iterator();
		while(itCA.hasNext()){
			ConfigAttribute ca = itCA.next();
			String needRole = ((SecurityConfig)ca).getAttribute();
			System.out.println("访问该资源需要角色  : " + needRole);
			for(GrantedAuthority ga : authentication.getAuthorities()){
				System.out.println("当前用户角色是 : " + ga.getAuthority());
				if(needRole.equals(ga.getAuthority())){
					System.out.println("访问允许，角色是  : " + ga.getAuthority());
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
