package com.genie.controller;
/*author:Genie
 *date:2017年4月11日
**/

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genie.service.LoginValid;


@Controller
@RequestMapping(value="/ms")
public class LoginController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private LoginValid loginValid;
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public void login(@RequestParam("username") String username, @RequestParam("password") String password,HttpServletResponse response){
		if(loginValid.loginValid(username, password)){
			HttpSession session = request.getSession();
			if(session.getAttribute("username") == null){
				session.setAttribute("username", username);
				request.changeSessionId();
			}
			try {
				response.sendRedirect("../html/admin.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
	}
	
	@RequestMapping(value="/getCsrfToken.do",method=RequestMethod.GET)
	public String getCsrfToken(){
		return "_csrf";
	}
	
	/*@RequestMapping(value="/login2.do",method=RequestMethod.POST)
	public String login2(@RequestParam("username2") String username,@RequestParam("password2") String password){
		System.out.println("进入login2");
		if(loginValid.loginValid(username, password)){
			HttpSession session = request.getSession();
			if(session.getAttribute("username") == null){
				session.setAttribute("username", username);
				session.setAttribute("oldSessionId", session.getId());
				request.changeSessionId();
				session.setAttribute("newSessionId", session.getId());
			}
			return "loginInfo";
		}
		else{
			return "failed";
		}
			
	}*/
	
	@RequestMapping(value="/logout.do",method=RequestMethod.GET)
	public String logout(){
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/html/login.html";
	}
	
	@RequestMapping(value="/userInfo.do",method=RequestMethod.GET)
	public String userInfo(){
		HttpSession session = request.getSession();
		
		System.out.println("session is :" + session.getId());
		return "userInfo";
		
	}

}
