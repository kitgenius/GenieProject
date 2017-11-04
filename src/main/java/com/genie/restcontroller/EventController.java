package com.genie.restcontroller;
/*author:Genie
 *date:2017年6月18日
**/

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.genie.entity.TEvent;
import com.genie.service.EventHandler;

@RestController
@RequestMapping(value="/rest")
public class EventController {

	@Autowired
	EventHandler eventHandler;
	
	@RequestMapping(value="/event/{code}",method = RequestMethod.GET)
	public TEvent getEvent(@PathVariable String code){
		List<TEvent> eventList;
		eventList = eventHandler.findEventByCode(code);
		if(eventList.isEmpty()){
			TEvent failed = new TEvent();
			failed.setCode("0");
			failed.setName("failed");
			return failed;
		}else{
			TEvent tEvent = eventList.get(0);
			return tEvent;
		}
		
	}
}
