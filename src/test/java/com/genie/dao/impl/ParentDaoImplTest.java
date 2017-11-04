package com.genie.dao.impl;
/*author:Genie
 *date:2017年10月17日
**/

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.genie.dao.ParentDao;
import com.genie.entity.Child;
import com.genie.entity.Parent;
import com.genie.entity.Sex;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
@WebAppConfiguration
@Transactional
public class ParentDaoImplTest {
	
	@Autowired
	ParentDao parentDao;
	
	@Test
	@Rollback(false)
	public void attachDirtyTest(){
		Child child = new Child();
		child.setChildName("boy1");
		child.setSex(Sex.MALE.getSexStr());
		HashSet<Child> childs = new HashSet<Child>();
		childs.add(child);
		Parent parent=new Parent("parent1",Sex.MALE.getSexStr(),new Integer(1),childs);
		parentDao.attachDirty(parent);
	}
}
