package com.genie.controller;
/*author:Genie
 *date:2017年4月11日
**/

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	@RequestMapping(value="/userInfo.do",method=RequestMethod.GET)
	public String userInfo(){
		HttpSession session = request.getSession();
		
		System.out.println("session is :" + session.getId());
		//return "userInfo";
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usernameStr = userDetails.getUsername();
		return "Hello " + usernameStr;
	}

}
