package com.genie.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.test.web.servlet.htmlunit.UrlRegexRequestMatcher;

import com.genie.dao.HibernateDao;
import com.genie.entity.Auth;
import com.genie.entity.Role;

/*author:Genie
 *date:2018年2月27日
**/
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private HibernateDao hibernateDao;//后续要修改成service
	
	/*private static Map<String, Collection<ConfigAttribute>> resourceMap = null;*/
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		//参数是要访问的url，返回这个url对应的所有权限（或角色）
		String requestUrl = ((FilterInvocation)object).getRequestUrl();//获取请求的url
		System.out.println(requestUrl);//临时调试
		
		//查找所有url对应的角色
		List<Auth> auths = hibernateDao.findAll(Auth.class);
		List<String> resourceUrls = new ArrayList<String>();
		
		//根据角色设置相应的属性，返回。
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		
		for(Auth auth:auths){
			resourceUrls.add(auth.getAuthPath());
			if(requestUrl.matches(auth.getAuthPath())){//请求的url能匹配上权限的url则将对应的角色设置到属性，最后返回
				List<Role> roles = (List<Role>) auth.getRole();
				for(Role role:roles){
					String roleNameStr = role.getRoleName();
					System.out.println("有权限的角色名：" + roleNameStr);
					ConfigAttribute ca = new SecurityConfig(roleNameStr);
					atts.add(ca);
				}
			}
		}		
		
		return atts;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	/*public MyInvocationSecurityMetadataSource() {
		loadResourceDefine();
	}*/
	
	//缓存，后续要修改缺陷
	/*private void loadResourceDefine(){
		resourceMap = new HashMap<String,Collection<ConfigAttribute>>();
		List<Auth> auths = hibernateDao.findAll(Auth.class);
		for(Auth auth:auths){
			String authPath = auth.getAuthPath();//设置资源
			
			Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();//设置可访问资源的属性-角色
			List<Role> roles = (List<Role>) auth.getRole();
			for(Role role:roles){
				ConfigAttribute ca = new SecurityConfig(role.getRoleName());
				atts.add(ca);
			}
			resourceMap.put(auth.getAuthPath(), atts);//绑定资源与角色
		}
	}*/

}
