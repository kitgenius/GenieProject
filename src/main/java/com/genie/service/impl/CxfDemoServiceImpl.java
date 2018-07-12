package com.genie.service.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.genie.entity.CxfEntity;
import com.genie.service.CxfDemoService;

//@Component
//@Service(value="cxfDemoService")
@WebService(endpointInterface = "com.genie.service.CxfDemoService")
@SOAPBinding(style = Style.RPC)
//@org.apache.cxf.interceptor.InInterceptors (interceptors = {"org.apache.cxf.transport.common.gzip.GZIPInInterceptor","org.apache.cxf.transport.common.gzip.GZIPOutInterceptor" })
public class CxfDemoServiceImpl extends BaseService implements CxfDemoService {

	@Override
	@WebMethod
	public List findAll() {
		return hibernateDao.findAll(CxfEntity.class);
	}

	
}
