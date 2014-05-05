package com.kozlov.library.beans;

import java.io.IOException;
import java.io.Serializable;
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
import com.kozlov.library.dao.UserDao;
import com.kozlov.library.enteties.Book;
import com.kozlov.library.enteties.Order;
import com.kozlov.library.enteties.OrderItem;
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
	private BookDao bookDao;
	private Order currOrder;
	private Book bookForOrder;

	@PostConstruct
	private void init() {
		currUser = new User();
		bookForOrder = new Book();
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

	public void addBookToOrder() {
		OrderItem orderItem = new OrderItem();
		orderItem.setBook(bookForOrder);
		currOrder.getOrderItems().add(orderItem);
	}
}
