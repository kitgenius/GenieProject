package com.genie.dao.impl;
/*author:Genie
 *date:2017年10月17日
**/

import java.util.HashSet;
import java.util.Set;

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
import com.genie.entity.Organ;
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
		child.setChildName("girl2");
		child.setSex(Sex.FEMALE.getSexStr());
		HashSet<Child> childs = new HashSet<Child>();
		childs.add(child);
		Parent parent=new Parent("parent2",Sex.MALE.getSexStr(),new Integer(1),childs);
		parentDao.attachDirty(parent);
	}
	
	@Test
	public void findByIdTest(){
		Parent parent = parentDao.findById(new Integer(6));
		Set<Child> childSet = parent.getChilds();
		for(Child child:childSet){
			System.out.println(child.getChildName());
		}
	}
	
	@Test
	public void findOrganByParentId(){
		Parent parent = parentDao.findById(new Integer(6));
		Set<Child> childSet = parent.getChilds();
		for(Child child:childSet){
			System.out.println(child.getChildName());
			Set<Organ> organSet = child.getOrgans();
			for(Organ organ:organSet){
				System.out.println(organ.getOrganName());
			}
		}
	}
}
