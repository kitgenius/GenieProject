package com.genie.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.test.web.servlet.htmlunit.UrlRegexRequestMatcher;

import com.genie.dao.HibernateDao;
import com.genie.entity.Auth;
import com.genie.entity.Role;
import com.genie.service.AuthService;

/**
 * author:Genie
 * ate:2018年2月27日
 * 用户的权限信息，注入SecurityInterceptor拦截器，拦截器通过getAttributes方法来获取被拦截url所需的全部权限
**/
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private AuthService authService;
	
	/*private static Map<String, Collection<ConfigAttribute>> resourceMap = null;*/
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		//参数是要访问的url，返回这个url对应的所有权限（或角色）
		String requestUrl = ((FilterInvocation)object).getRequestUrl();//获取请求的url
		System.out.println("MyInvocationSecurityMetadataSource获取访问资源所需权限，用户正在访问资源 ： " + requestUrl);//临时调试
		
		//查找所有url对应的角色
		List<Auth> auths = authService.findAll();
		
		//根据角色设置相应的属性，返回。
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		
		for(Auth auth:auths){
			System.out.println(requestUrl + " 与 " + auth.getAuthPath() + "的匹配结果：" + requestUrl.matches(auth.getAuthPath()));
			//System.out.println(requestUrl + " 与 " + auth.getAuthPath() + "的匹配结果：" + Pattern.matches(auth.getAuthPath(), requestUrl));
			if(requestUrl.matches(auth.getAuthPath())){//请求的url能匹配上权限的url则将对应的角色设置到属性，最后返回
				System.out.println("MyInvocationSecurityMetadataSource权限表有对应的角色具备权限");
				List<Role> roles = (List<Role>) auth.getRole();
				System.out.println("roles size : " + roles.size());
				for(Role role:roles){
					String roleNameStr = role.getRoleName();
					System.out.println("MyInvocationSecurityMetadataSource有权限访问该资源的角色名 ：" + roleNameStr);
					ConfigAttribute ca = new SecurityConfig(roleNameStr);
					atts.add(ca);
				}
			}
		}
		
		System.out.println("atts是否为空(没有该资源的对应权限)：" + atts.isEmpty());
		
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
