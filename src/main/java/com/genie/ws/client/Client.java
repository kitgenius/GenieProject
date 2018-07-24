package com.genie.ws.client;

import java.util.List;

import com.genie.ws.service.DemoService;
import com.genie.ws.service.impl.CxfDemoService;
import com.genie.ws.service.impl.CxfEntity;
import com.genie.ws.service.impl.CxfEntityArray;

public class Client {

	public static void main(String[] args) {
		DemoService demoService = new DemoService();
		CxfDemoService cxfDemoService = demoService.getDemoPort();
		CxfEntityArray results = cxfDemoService.findAll();
		List<CxfEntity> items = results.getItem();
		for(CxfEntity e:items){
			System.out.println(e.getId() + ":" + e.getCode() + ":" + e.getName());
		}
		
	}

}
