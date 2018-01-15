/*package com.genie.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.genie.dao.LoginDao;
import com.genie.entity.Login;

author:Genie
 *date:2017年10月10日
*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
@Transactional
public class LoginDaoImplTest {
	@Autowired
	LoginDao loginDao;
	
	@Test
	@Rollback(false)
	public void persistTest(){
		Login loginEntity = new Login("junitTestPersist", "password");
		loginDao.persist(loginEntity);
	}
	
	@Test
	@Rollback(false)
	public void deleteTest(){
		Login loginEntity = new Login();
		loginEntity.setUsername("test");
		Login deleteEntity = loginDao.findByExample(loginEntity).get(0);
		loginDao.delete(deleteEntity);
	}
	
	@Test
	@Rollback(false)
	public void mergeTest(){
		Login loginEntity = new Login("junitTestMerge", "password");
		loginDao.merge(loginEntity);
	}
	
	@Test
	@Rollback(false)
	public void attachDirtyTest(){
		Login loginEntity = new Login("junitTestAttachDirty2", "password2");
		loginEntity.setId(new Integer(14));
		loginDao.attachDirty(loginEntity);
	}
	
	@Test
	@Rollback(false)
	public void attachCleanTest(){
		Login loginEntity = new Login("junitTestAttachClean", "password");
		loginDao.attachDirty(loginEntity);
	}
}
*/