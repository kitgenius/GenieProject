/*package com.genie.dao.impl;
author:Genie
 *date:2017年4月11日
*

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.genie.dao.LoginDao;
import com.genie.entity.Login;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
@Transactional
public class TLoginHomeTest {

	@Autowired
	private LoginDao tLoginDao;

	@Test
	public void findByExampleTest() {
		Login instance = new Login();
		instance.setUsername("genie");
		instance.setPassword("genie");
		List<Login> tLoginList = tLoginDao.findByExample(instance);
		String username = tLoginList.get(0).getUsername();
		String password = tLoginList.get(0).getPassword();

		assertEquals(username, "genie");
		assertEquals(password, "genie");
	}
}
*/