package com.genie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genie.dao.impl.TEventHome;
import com.genie.entity.TEvent;
import com.genie.service.EventHandler;

/*author:Genie
 *date:2017年6月18日
**/
@Service("eventHandler")
public class EventHandlerImpl implements EventHandler {

	@Autowired
	TEventHome tEventDao; 
	
	@Override
	public List findEventByCode(String code) {
		TEvent instance = new TEvent();
		instance.setCode(code);
		List<TEvent> eventList = tEventDao.findByExample(instance);
		return eventList;
	}
	
}
