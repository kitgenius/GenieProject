package com.genie.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.genie.entity.CxfEntity;

@WebService
@SOAPBinding(style = Style.RPC)
public interface CxfDemoService {
	@WebMethod
	public List<CxfEntity> findAll();
}
