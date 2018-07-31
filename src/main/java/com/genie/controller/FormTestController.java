package com.genie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.genie.model.ResponseModel;
import com.genie.model.form.UserInfoForm;

@RestController
@RequestMapping(value="/FormTest")
public class FormTestController {
	
	@RequestMapping(value="test",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseModel test(UserInfoForm userInfoForm){
		System.out.println("in FormTest test");
		ResponseModel res = new ResponseModel();
		
		System.out.println(userInfoForm.getUsername());
		
		res.setData(userInfoForm.getUsername() + "数据已成功提交");
		res.setStatus(true);
		res.setMsg("成功");
		res.setCode("G200");
		return res;
	}
	
	/**
	 * 接收json格式正文的请求，必须指定Content-Type
	 * get方法不能带正文，返回Bad Request
	 * @param userInfoForm
	 * @return
	 */
	@RequestMapping(value="testJson",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseModel testJson(@RequestBody UserInfoForm userInfoForm){
		ResponseModel res = new ResponseModel();
		
		System.out.println(userInfoForm.getUsername());
		
		res.setData(userInfoForm);
		res.setStatus(true);
		res.setMsg("成功");
		res.setCode("G200");
		return res;
	}
	
	@RequestMapping(value="hello",method=RequestMethod.GET)
	@ResponseBody
	public String hello(){
		return "hello";
	}
}
