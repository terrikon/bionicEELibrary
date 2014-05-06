package com.kozlov.library.dao;

import javax.ejb.Stateless;


import com.kozlov.library.enteties.OrderStatus;
@Stateless
public class OrderStatusDao extends GeneralDao<OrderStatus,Integer> {

	public OrderStatusDao() {
		super(OrderStatus.class);
	
}
}
