package com.genie.fssb.model;
/*author:Genie
 *date:2017年4月10日
**/

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="loginUserTest")
public class LoginUser {
	
	String username;
	String password;
	
	public String getUsername() {
		return username;
	}
	@XmlAttribute(name="userName")
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
