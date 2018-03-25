package com.genie.aspect;
/*author:Genie
 *date:2018年3月25日
**/

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyAspect {

	@Pointcut("execution(* com.genie.dao.impl.HibernateDaoImpl.findAll(..))")
	public void myPointcut(){
		
	}
	
	@Before("myPointcut()")
	public void beforeExe(){
		System.out.println("调用方法之前执行");
	}
	
	
	
	@AfterReturning("myPointcut()")
	public void afterReturnExe(){
		System.out.println("调用方法并返回后执行");
	}
	
	//After在AfterReturning之前执行
	@After("myPointcut()")
	public void afterExe(){
		System.out.println("调用方法之后(或异常)执行");
	}
}
