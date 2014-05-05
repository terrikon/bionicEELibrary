package com.kozlov.library.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.kozlov.library.enteties.OrderItem;

@Stateless
public class OrderItemDao extends GeneralDao<OrderItem, Integer> {
	public OrderItemDao() {
		super(OrderItem.class);
	}

	public Integer amountBookByNameInUse(String name) {
		TypedQuery<Integer> query = em.createNamedQuery("OrderItem.countBook",
				Integer.class);
		query.setParameter("name", name);
		Integer result = query.getSingleResult();
		return result;
	}
}
