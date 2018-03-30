package com.genie.controller;
/*author:Genie
 *date:2018年3月14日
**/

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@ResponseBody
	@RequestMapping(value="/userInfo.do",method=RequestMethod.GET)
	public UserDetails userInfo(){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String usernameStr = userDetails.getUsername();
		return userDetails;
	}
	
	@ResponseBody
	@RequestMapping(value="/userTest.do",method=RequestMethod.GET)
	public String userTest(){
		return "userTest";
	}
}