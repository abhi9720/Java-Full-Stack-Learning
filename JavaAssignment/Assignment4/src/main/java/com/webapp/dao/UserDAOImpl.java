package com.webapp.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webapp.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public User authenticateUser(String username, String password) {
		Query query = getSession().createQuery("FROM User WHERE username = :username AND password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);

		User user = (User) query.uniqueResult();
		return user;
	}

}
