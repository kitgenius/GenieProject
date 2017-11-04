package com.genie.service;

import java.util.List;

import com.genie.entity.Child;
import com.genie.entity.Parent;

/*author:Genie
 *date:2017年10月24日
 *成员管理
**/
public interface MemberManage {
	boolean addMember(Parent parent,Child child);
	boolean deleteMember(Parent parent,Child child);
	List<Child> findChildByParent(Parent parent);
	//List<Child> findChildByOrgan();
}
