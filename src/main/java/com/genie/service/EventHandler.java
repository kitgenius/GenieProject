package com.genie.service;

import java.util.List;

import com.genie.entity.TEvent;

/*author:Genie
 *date:2017年6月18日
**/
public interface EventHandler {
	public List findEventByCode(String code);
}
