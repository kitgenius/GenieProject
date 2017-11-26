package com.genie.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genie.dao.ParentDao;
import com.genie.entity.Child;
import com.genie.entity.Parent;

/*author:Genie
 *date:2017年11月26日
**/
@Service("mamberManage")
public class MemberManageImpl implements com.genie.service.MemberManage {

	@Autowired
	ParentDao parentDao;
	
	@Override
	public boolean addMember(Parent parent, Child child) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMember(Parent parent, Child child) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Parent> findAllParents() {
		/*List parentList = new LinkedList<>();
		for(Parent parent:parentDao.findAll()){
			parentList.add(parent);
		}
		return parentList;*/
		return parentDao.findAll();
	}

	@Override
	public List<Child> findChildByParent(Parent parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
