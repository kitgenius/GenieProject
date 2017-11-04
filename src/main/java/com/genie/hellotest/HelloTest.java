package com.genie.hellotest;

import org.springframework.stereotype.Component;

/*author:Genie
 *date:2017年9月11日
**/

@Component("helloTest")
public class HelloTest {
	public String sayHello(String name){
		System.out.println("Hello, " + name);
		return name;
	}
}
