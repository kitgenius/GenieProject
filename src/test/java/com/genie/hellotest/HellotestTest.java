package com.genie.hellotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/*author:Genie
 *测试用的
 *date:2017年9月11日
**/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class HellotestTest {
	@Autowired
	HelloTest helloTest;
	
	@Test
	public void sayHelloTest(){
		String result = helloTest.sayHello("tester");
		System.out.println("result is " + result);
		assertEquals(result,"tester!");
	}
}
