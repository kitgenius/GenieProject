package com.genie.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.genie.dao.HibernateDao;
import com.genie.entity.ManagerUser;
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
				assertEquals("ROLE_ORDINARY",role.getRoleName());
				assertEquals("普通用户",role.getDescription());
				break;
			}
			
			System.out.println("description: " + role.getDescription());
			i++;
		}
	}
	
	@Test
	public void queryByExampleTest(){
		ManagerUser login = new ManagerUser();
		login.setUsername("genie");
		List<ManagerUser> LoginList = hibernateDao.findByExample(ManagerUser.class, login);
		assertEquals("genie",LoginList.get(0).getPassword());
	}
	
	@Test
	public void findByPropertyTest(){
		List<ManagerUser> loginList = hibernateDao.findByProperty(ManagerUser.class, Restrictions.like("username", "test%"));
		for(ManagerUser login:loginList){
			System.out.println("login name is : " + login.getUsername());
		}
		System.out.println("another test");
		List<ManagerUser> loginList2 = hibernateDao.findByProperty(ManagerUser.class, Restrictions.eq("username", "genie"));
		for(ManagerUser login2:loginList2){
			System.out.println("login name is : " + login2.getUsername());
			System.out.println("login password is : " + login2.getPassword());
			List<Role> roles = (List<Role>) login2.getRole();
			System.out.println("roles length : " + roles.toArray().length);
			for(Role role : roles){
				System.out.println(role.getRoleName());
			}
		}
	}
	
	@Test
	public void findByCriterionsTest(){
		List<Criterion> restrictionsList = new ArrayList();
		restrictionsList.add(Restrictions.like("username", "test%"));
		restrictionsList.add(Restrictions.like("password", "%2%"));
		List<ManagerUser> loginList = hibernateDao.findByCriterions(ManagerUser.class, restrictionsList);
		for(ManagerUser login:loginList){
			System.out.println("login name is : " + login.getUsername());
		}
	}
	
	@Test
	public void findByCriterionsTest2(){
		List<ManagerUser> loginList = null;
		List<Criterion> restrictionsList = new ArrayList();
		restrictionsList.add(Restrictions.like("username", "junit%"));
		restrictionsList.add(Restrictions.like("password", "pass%"));
		loginList = hibernateDao.findByCriterions(ManagerUser.class, restrictionsList,0,2);
		for(ManagerUser login:loginList){
			System.out.println("login name is : " + login.getUsername());
		}
	}
	
	@Test
	public void findBySQLQueryTest(){
		List<ManagerUser> loginList = null;
		String sqlStr = "select {login.*} from t_login login";
		loginList = hibernateDao.findBySQLQuery(sqlStr, "login", ManagerUser.class);
		for(ManagerUser login:loginList){
			System.out.println("login name is : " + login.getUsername());
		}
		
	}
	
	@Test
	public void findByHQLQueryTest(){
		String hqlStr = "select login.username from Login login where login.username = 'genie2'";
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
		List<ManagerUser> loginList = hibernateDao.findByNamedParam(hqlStr, paramNames, values);
		for(ManagerUser login:loginList){
			System.out.println("username is : " + login.getUsername() +  "\n" + "password is : " + login.getPassword());
		}
	}
	
	@Test
	public void findByNamedParamTest2(){
		String hqlStr="from Login login where login.username like :username";
		String[] paramNames = {"username"};
		String[] values = {"junit%"};
		List<ManagerUser> loginList = hibernateDao.findByNamedParam(hqlStr, paramNames, values, 2, 2);
		for(ManagerUser login:loginList){
			System.out.println("username is : " + login.getUsername());
		}
	}
	
	@Test
	public void findByValueBeanTest(){
		String hqlStr="from Login login where login.username like :username and login.password like :password";
		ManagerUser valueBean = new ManagerUser();
		valueBean.setUsername("genie%");
		valueBean.setPassword("genie");
		List<ManagerUser> loginList = hibernateDao.findByValueBean(hqlStr, valueBean);
		for(ManagerUser login:loginList){
			System.out.println("username is :" + login.getUsername());
		}
	}
	
	@Test
	public void findByNamedQueryTest(){
		List<ManagerUser> loginList= hibernateDao.findByNamedQuery("getLoginByUsername");
		for(ManagerUser login:loginList){
			System.out.println("username is : " + login.getUsername());
		}
	}
	
	@Test
	public void findByNameQueryTest2(){
		List<ManagerUser> loginList= hibernateDao.findByNamedQuery("getLoginByUsername2","genie");
		for(ManagerUser login:loginList){
			System.out.println("username is : " + login.getUsername());
		}
	}
	
	@Test
	public void findByNamedQuery3(){
		String[] strArray = new String[] {"test%","test1"};
		List<ManagerUser> loginList= hibernateDao.findByNamedQuery("getLoginByUsername3",strArray);
		for(ManagerUser login:loginList){
			System.out.println("username is : " + login.getUsername());
		}
	}
	
	@Test
	public void findByNamedQueryAndNamedParamTest(){
		String namedQuery = "getLoginByUsername4";
		String[] paramNames = new String[] {"username","password"};
		String[] values = new String[] {"genie%","genie"};
		List<ManagerUser> loginList = hibernateDao.findByNamedQueryAndNamedParam(namedQuery, paramNames, values);
		for(ManagerUser login:loginList){
			System.out.println("username is : " + login.getUsername());
			System.out.println("password is : " + login.getPassword());
		}
	}
	
	@Test
	public void findByNamedQueryAndValueBeanTest(){
		String namedQuery = "getLoginByUsername4";
		ManagerUser login = new ManagerUser();
		login.setUsername("genie%");
		login.setPassword("genie");
		List<ManagerUser> loginList = hibernateDao.findByNamedQueryAndValueBean(namedQuery, login);
		for(ManagerUser loginResult:loginList){
			System.out.println("username is : " + loginResult.getUsername());
			System.out.println("password is : " + loginResult.getPassword());
		}
	}
	
	@Test
	@Rollback(false)
	public void saveTest(){
		ManagerUser login = new ManagerUser();
		login.setUsername("saveTest");
		login.setPassword("saveTest");
		hibernateDao.save(login);
	}
	
	@Test
	@Rollback(false)
	public void saveOrUpdateTest(){
		ManagerUser login = new ManagerUser();
		login.setUsername("saveTest");
		login.setPassword("saveTest2");
		hibernateDao.saveOrUpdate(login);
	}
	
	@Test
	@Rollback(false)
	public void saveOrUpdateTest2(){
		Criterion restriction = Restrictions.eq("password", "saveTest");
		List<ManagerUser> loginList = hibernateDao.findByProperty(ManagerUser.class, restriction);
		ManagerUser login = loginList.get(0);
		login.setPassword("newPassword");
		hibernateDao.saveOrUpdate(login);
	}
	
	@Test
	@Rollback(false)
	public void deleteTest(){
		Criterion restriction = Restrictions.eq("password", "newPassword");
		List<ManagerUser> loginList = hibernateDao.findByProperty(ManagerUser.class, restriction);
		ManagerUser login = loginList.get(0);
		hibernateDao.delete(login);
	}
}
