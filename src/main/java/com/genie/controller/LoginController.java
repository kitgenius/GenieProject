package com.genie.controller;
/*author:Genie
 *date:2017年4月11日
**/

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	private HttpServletRequest request;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	//获取spring security的csrf token
	@RequestMapping(value="/getCsrfToken",method=RequestMethod.GET)
	public String getCsrfToken(){
		return "_csrf";
	}
	
	@RequestMapping(value="/logout.do",method=RequestMethod.GET)
	public String logout(){
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/html/login.html";
	}
	
	@RequestMapping(value="index.do",method=RequestMethod.GET)
	public String index(){
		HttpSession session = request.getSession();
		return "redirect:/html/index.html";
	}

	@ResponseBody
	@RequestMapping(value="/success.do")
	public Map<String, Object> success(HttpServletRequest request, HttpServletResponse response){
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String targetUrl = "http://localhost:8080/GenieProject/html/index.html";
		if (savedRequest != null) {
            targetUrl = savedRequest.getRedirectUrl();
        }
		Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("resultCode", 200);
        result.put("targetUrl", targetUrl);
        return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/failure.do")
	public Map<String, Object> failure(HttpServletRequest request, HttpServletResponse response){
		AuthenticationException ae = (AuthenticationException) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("resultCode", 403);
        result.put("message", ae.getMessage());
        return result;
	}
}
