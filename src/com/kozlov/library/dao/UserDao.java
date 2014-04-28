package com.kozlov.library.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.kozlov.library.enteties.User;

@Stateless
public class UserDao extends GeneralDao<User, Integer> {

	public UserDao() {
		super(User.class);
	}

	public User find(String login) {
		TypedQuery<User> query = em.createNamedQuery("User.findByLogin",
				User.class);
		User result = query.getSingleResult();
		return result;
	}

}
