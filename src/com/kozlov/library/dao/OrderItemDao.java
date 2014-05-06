package com.kozlov.library.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.kozlov.library.enteties.Book;
import com.kozlov.library.enteties.OrderItem;

@Stateless
public class OrderItemDao extends GeneralDao<OrderItem, Integer> {
	public OrderItemDao() {
		super(OrderItem.class);
	}

	public Integer amountBookByNameInUse(Book book) {
		TypedQuery<Long> query = em.createNamedQuery("OrderItem.countBook",
				Long.class);
		query.setParameter("book", book);
		Integer result = query.getSingleResult().intValue();
		return result;
	}
}
