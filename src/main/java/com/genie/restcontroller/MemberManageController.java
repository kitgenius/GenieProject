package com.genie.restcontroller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genie.entity.Parent;
import com.genie.model.ParentVO;
import com.genie.service.MemberManage;

/*author:Genie
 *date:2017年11月26日
**/
@RestController
@RequestMapping("/memberManage")
public class MemberManageController {

	@Autowired
	private MemberManage memberManage;
	
	@RequestMapping(value="parentInfo.do",method=RequestMethod.GET)
	public List<ParentVO> parentInfo(){
		List<ParentVO> parentVOList = new LinkedList<ParentVO>();
		List<Parent> temp = memberManage.findAllParents();
		
		for(Parent parentEntity:temp){
			ParentVO parentVO = new ParentVO();
			parentVO.setPersonId(parentEntity.getPersonId());
			parentVO.setParentName(parentEntity.getParentName());
			parentVO.setSex(parentEntity.getSex());
			parentVOList.add(parentVO);
		}
		
		return parentVOList;
	}
}
