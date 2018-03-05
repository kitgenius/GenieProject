package com.genie.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.genie.entity.Login;
import com.genie.model.LoginVO;
import com.genie.model.ResponseModel;
import com.genie.service.LoginManage;

/*author:Genie
 *date:2017年10月11日
**/
@RestController
@RequestMapping("/loginManage")
public class LoginManageController {
	
	/*@Autowired
	private LoginManage loginManage;*/
	
	@Autowired
	private HttpServletRequest request;
	
	/*@RequestMapping(value = "/loginInfo.do", method = RequestMethod.GET)
	public LoginVO loginInfo() {
		LoginVO loginVO = new LoginVO();
		HttpSession session = request.getSession();
		if(session.getAttribute("username") != null){
			String username = (String) session.getAttribute("username");
			Login loginEntity = loginManage.findLogin(username);
			loginVO.setUsername(loginEntity.getUsername());
			loginVO.setPassword(loginEntity.getPassword());
			return loginVO;
		}else{
			return null;
		}
	}*/
	
	/*@RequestMapping(value = "addLogin.do", method = RequestMethod.POST)
	public ResponseModel addLogin(@RequestParam String username,@RequestParam String password){
		ResponseModel resp = new ResponseModel();
		boolean status = loginManage.addLogin(username, password);
		String msg;
		LoginVO loginVO = new LoginVO();
		if(status){
			msg="添加成功！";
			Login loginEntity=loginManage.findLogin(username);
			loginVO.setUsername(loginEntity.getUsername());
			loginVO.setPassword(loginEntity.getPassword());
		}else{
			msg="添加失败！";
		}
		resp.setStatus(status);
		resp.setMsg(msg);
		resp.setData(loginVO);
		return resp;
	}*/
}
