package com.genie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.genie.dao.HibernateDao;

public class BaseService {
	@Autowired
	HibernateDao hibernateDao;
}
