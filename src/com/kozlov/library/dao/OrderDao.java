package com.kozlov.library.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.kozlov.library.enteties.Order;
import com.kozlov.library.enteties.Order;


@Stateless
public class OrderDao extends GeneralDao<Order, Integer> {
	public OrderDao() {
		super(Order.class);
	}

	public List<Order> findAll() {
		TypedQuery<Order> query = em.createNamedQuery("Order.findAll",Order.class);
		List<Order> results = query.getResultList();
		return results;
	}
	public List<Order> find(String login) {
		TypedQuery<Order> query = em.createNamedQuery("Order.findByLogin",
				Order.class);
		query.setParameter("login",login);
		List<Order> results = query.getResultList();
		return results;
	}
}
