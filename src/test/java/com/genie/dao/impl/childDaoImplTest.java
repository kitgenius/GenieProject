package com.genie.dao.impl;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.genie.dao.ChildDao;
import com.genie.entity.Child;
import com.genie.entity.Parent;
import com.genie.entity.Sex;

/*author:Genie
 *date:2017年10月23日
**/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
@WebAppConfiguration
@Transactional
public class childDaoImplTest {

	@Autowired
	private ChildDao childDao;
	
	@Test
	@Rollback(false)
	public void attachDirtyTest(){
		Child child = new Child();
		child.setChildName("boy2");
		child.setSex(Sex.MALE.getSexStr());
		HashSet<Child> childs = new HashSet<Child>();
		childs.add(child);
		Parent parent=new Parent("parent2",Sex.MALE.getSexStr(),new Integer(1),childs);
		childDao.attachDirty(child);
	}
	
}
