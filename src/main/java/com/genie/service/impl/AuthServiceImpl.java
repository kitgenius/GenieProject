package com.genie.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.genie.entity.Auth;
import com.genie.service.AuthService;

@Service("authService")
@Transactional
public class AuthServiceImpl extends BaseService implements AuthService {

	@Override
	public List<Auth> findAll() {
		return hibernateDao.findAll(Auth.class);
	}

}
