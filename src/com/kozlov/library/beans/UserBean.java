package com.kozlov.library.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.kozlov.library.dao.BookDao;
import com.kozlov.library.dao.OrderDao;
import com.kozlov.library.dao.OrderItemDao;
import com.kozlov.library.dao.OrderStatusDao;
import com.kozlov.library.dao.UserDao;
import com.kozlov.library.enteties.Book;
import com.kozlov.library.enteties.Order;
import com.kozlov.library.enteties.OrderItem;
import com.kozlov.library.enteties.OrderStatus;
import com.kozlov.library.enteties.User;

@Named
@SessionScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserDao userDao;
	private User currUser;
	@Inject
	private OrderDao orderDao;
	@Inject
	private OrderItemDao orderItemDao;
	@Inject
	private OrderStatusDao orderStatusDao;
	@Inject
	private BookDao bookDao;
	private Order currOrder;
	private Book bookForOrder;
	private OrderItem currOrderItem;

	@PostConstruct
	private void init() {
		currUser = new User();
		bookForOrder = new Book();
		currOrder = new Order();
		currOrderItem = new OrderItem();
	}

	public User getCurrUser() {
		return currUser;
	}

	public void setCurrUser(User currUser) {
		this.currUser = currUser;
	}

	public String login() throws IOException {
		try {
			getRequest().login(currUser.getLogin(), currUser.getPass());
			currUser = userDao.find(currUser.getLogin());
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Wellcome " + currUser.getName()));
		} catch (ServletException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
							"Incorrect login or password"));
			return null;
		}
		return goTo(currUser.getUserType().getType());

	}

	public String logout() throws ServletException {
		currUser = null;
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		if (isAuthenticated()) {
			getRequest().logout();
			return "/faces/pages/guest/indexGuest";
		}
		return null;
	}

	public boolean isAuthenticated() {
		return getRequest().getUserPrincipal() != null;
	}

	public static HttpServletRequest getRequest() {
		Object request = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		return (HttpServletRequest) request;
	}

	public String goTo(String userType) {
		switch (userType) {
		case "librarian":
			return "indexLibrarian";
		case "admin":
			return "indexAdmin";
		case "reader":
			return "indexReader";
		default:
			return "indexGuest";
		}
	}

	public Order getCurrOrder() {
		return currOrder;
	}

	public void setCurrOrder(Order currOrder) {
		this.currOrder = currOrder;
	}

	public Book getBookForOrder() {
		return bookForOrder;
	}

	public void setBookForOrder(Book bookForOrder) {
		this.bookForOrder = bookForOrder;
	}

	public OrderItem getCurrOrderItem() {
		return currOrderItem;
	}

	public void setCurrOrderItem(OrderItem currOrderItem) {
		this.currOrderItem = currOrderItem;
	}

	public String addBookToOrder() {
		OrderItem orderItem = new OrderItem();
		orderItem.setBook(bookForOrder);
		currOrder.getOrderItems().add(orderItem);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Book "
						+ bookForOrder.getTitle() + " added to order"));
		return "indexReader";
	}

	public List<OrderItem> getOrderItems() {
		return new ArrayList<>(currOrder.getOrderItems());
	}

	public void delBookFromOrder() {
		currOrder.getOrderItems().remove(currOrderItem);
	}

	public void submitOrder() {
		currOrder.setUser(currUser);
		currOrder.setDate(new Date());
		OrderStatus orderStatus = orderStatusDao.read(1);
		currOrder.setOrderStatus(orderStatus);
		orderDao.create(currOrder);
		currOrder = new Order();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
						"Now you can go to librarian to receive your books"));
	}
	
	public boolean isDisabled(){
		return currOrder.getOrderItems().isEmpty();
	}
	public boolean isBookInOrder(Book book){
		boolean result=false;
		for(OrderItem oi:currOrder.getOrderItems()){
		  if (oi.getBook().equals(book)){
			  result=true;
		  }
	  }
		return result;
	}

}
