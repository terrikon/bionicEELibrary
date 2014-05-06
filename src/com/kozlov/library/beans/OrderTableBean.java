package com.kozlov.library.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.kozlov.library.dao.OrderDao;
import com.kozlov.library.enteties.Order;

@Named
@RequestScoped
public class OrderTableBean {
	@Inject
	private UserBean user;
	@Inject
	private OrderDao orderdao;

	private List<Order> filteredOrders;

	private List<Order> orders;

	private Order selectedOrder;

	@PostConstruct
	public void setOrders() {
		orders = orderdao.find(user.getCurrUser().getLogin());
	}

	public Order getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(Order selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public List<Order> getFilteredOrders() {
		return filteredOrders;
	}

	public void setFilteredOrders(List<Order> filteredOrders) {
		this.filteredOrders = filteredOrders;
	}

	public List<Order> getOrders() {
		orders = orderdao.find(user.getCurrUser().getLogin());
		return orders;
	}
}
