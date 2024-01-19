package com.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.dao.UserDAO;
import com.webapp.model.User;

@Service
@Transactional	
public class UserServiceImpl implements UserService {
	UserDAO userdao;
	
	@Autowired
	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}

	public User authenticateUser(String username, String password) {
		return userdao.authenticateUser(username, password);
	}

}
