package com.kozlov.library.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.kozlov.library.dao.BookDao;
import com.kozlov.library.dao.OrderDao;
import com.kozlov.library.dao.OrderItemDao;
import com.kozlov.library.enteties.Book;
import com.kozlov.library.enteties.Order;
import com.kozlov.library.enteties.OrderItem;

import java.io.Serializable;

@Named
@SessionScoped
public class OrderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private OrderDao orderDao;
	@Inject
	private OrderItemDao orderItemDao;
	@Inject
	private BookDao bookDao;
	private Order currOrder;

	@PostConstruct
	private void init() {
		currOrder = new Order();

	}

	public Order getCurrOrder() {
		return currOrder;
	}

	public void setCurrOrder(Order currOrder) {
		this.currOrder = currOrder;
	}

	public void addBookToOrder(String stringBookId) {
		int bookId = Integer.parseInt(stringBookId);
		Book book = bookDao.read(bookId);
		OrderItem orderItem = new OrderItem();
		orderItem.setBook(book);
		currOrder.getOrderItems().add(orderItem);
	}
	
	}
