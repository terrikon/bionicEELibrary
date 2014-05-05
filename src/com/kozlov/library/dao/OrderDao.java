package com.kozlov.library.dao;

import javax.ejb.Stateless;

import com.kozlov.library.enteties.Order;

@Stateless
public class OrderDao extends GeneralDao<Order, Integer> {
	public OrderDao() {
		super(Order.class);
	}
}
