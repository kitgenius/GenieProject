package com.genie.dao.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.genie.dao.HibernateDao;
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
		for(Role role : roleList){
			System.out.println("id is " + role.getId());
			System.out.println("Role name is " + role.getRoleName());
			System.out.println("description: " + role.getDescription());
		}
	}
}
