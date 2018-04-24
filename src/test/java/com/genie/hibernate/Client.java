package com.genie.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db2.hibernate.generic.HibernateSessionFactory;

public class Client {
	public static void main(String[]args) {
		Client client = new Client();
		System.out.println("开始测试！！！");
		client.test1();
	}
	
	public void test1() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("from CwgkfmorgEntity");
		
		for(CwgkfmorgEntity org:(List<CwgkfmorgEntity>)query.list()) {
			System.out.println(org.getOrgCode()+":::"+org.getOrgName()+":::"+org.getParentCode().getOrgCode());
		}
		
		
		tx.commit();
		HibernateSessionFactory.closeSession();
	}
}
