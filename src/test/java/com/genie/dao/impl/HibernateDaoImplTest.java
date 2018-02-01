package com.genie.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
}
