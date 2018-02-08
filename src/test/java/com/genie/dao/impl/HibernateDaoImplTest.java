package com.genie.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.genie.dao.HibernateDao;
import com.genie.entity.Login;
import com.genie.entity.Role;



/*author:Genie
 *date:2018年1月15日
**/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-test.xml")
@Transactional
public class HibernateDaoImplTest {
	
	@Autowired
	HibernateDao hibernateDao;
	
	@Test
	public void findAllTest(){
		List<Role> roleList = hibernateDao.findAll(Role.class);
		int i =1;
		for(Role role : roleList){
			System.out.println("id is " + role.getId());
			assertEquals(i,role.getId());
			
			System.out.println("Role name is " + role.getRoleName());
			switch (i)
			{
			case 1:
				assertEquals("ROLE_ADMIN",role.getRoleName());
				assertEquals("管理员",role.getDescription());
				break;
			case 2 :
				assertEquals("ROLE_USER",role.getRoleName());
				assertEquals("普通用户",role.getDescription());
				break;
			}
			
			System.out.println("description: " + role.getDescription());
			i++;
		}
	}
	
	@Test
	public void queryByExampleTest(){
		Login login = new Login();
		login.setUsername("genie");
		List<Login> LoginList = hibernateDao.queryByExample(Login.class, login);
		assertEquals("genie",LoginList.get(0).getPassword());
	}
	
	@Test
	public void findByPropertyTest(){
		List<Login> loginList = hibernateDao.findByProperty(Login.class, Restrictions.like("username", "test%"));
		for(Login login:loginList){
			System.out.println("login name is : " + login.getUsername());
		}
	}
	
	@Test
	public void findByCriterionsTest(){
		List<Criterion> restrictionsList = new ArrayList();
		restrictionsList.add(Restrictions.like("username", "test%"));
		restrictionsList.add(Restrictions.like("password", "%2%"));
		List<Login> loginList = hibernateDao.findByCriterions(Login.class, restrictionsList);
		for(Login login:loginList){
			System.out.println("login name is : " + login.getUsername());
		}
	}
	
	@Test
	public void findByCriterionsTest2(){
		List<Login> loginList = null;
		List<Criterion> restrictionsList = new ArrayList();
		restrictionsList.add(Restrictions.like("username", "junit%"));
		restrictionsList.add(Restrictions.like("password", "pass%"));
		loginList = hibernateDao.findByCriterions(Login.class, restrictionsList,0,2);
		for(Login login:loginList){
			System.out.println("login name is : " + login.getUsername());
		}
	}
	
	@Test
	public void findBySQLQueryTest(){
		List<Login> loginList = null;
		String sqlStr = "select {login.*} from t_login login";
		loginList = hibernateDao.findBySQLQuery(sqlStr, "login", Login.class);
		for(Login login:loginList){
			System.out.println("login name is : " + login.getUsername());
		}
		
	}
	
	@Test
	public void findByHQLQueryTest(){
		String hqlStr = "select login.username from Login login where login.username like 'genie%'";
		List list = hibernateDao.findByHQLQuery(hqlStr);
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println("login username is : " + it.next());
		}
	}
	
	@Test
	public void findByNamedParamTest(){
		String hqlStr="from Login login where login.username = :username and login.password = :password";
		String[] paramNames = new String[2];
		paramNames[0]="username";
		paramNames[1]="password";
		String[] values = new String[2];
		values[0] = "genie";
		values[1] = "genie";
		List<Login> loginList = hibernateDao.findByNamedParam(hqlStr, paramNames, values);
		for(Login login:loginList){
			System.out.println("username is : " + login.getUsername() +  "\n" + "password is : " + login.getPassword());
		}
	}
	
	@Test
	public void findByNamedParamTest2(){
		String hqlStr="from Login login where login.username like :username";
		String[] paramNames = {"username"};
		String[] values = {"junit%"};
		List<Login> loginList = hibernateDao.findByNamedParam(hqlStr, paramNames, values, 2, 2);
		for(Login login:loginList){
			System.out.println("username is : " + login.getUsername());
		}
	}
}
