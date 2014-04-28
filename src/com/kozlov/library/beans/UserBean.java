package com.kozlov.library.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.kozlov.library.dao.UserDao;
import com.kozlov.library.enteties.User;

@Named
@SessionScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserDao userDao;
	private User currUser;

	@PostConstruct
	private void init() {
		currUser = new User();
	}

	public User getCurrUser() {
		return currUser;
	}

	public void setCurrUser(User currUser) {
		this.currUser = currUser;
	}

	public String login(String login, String password) throws IOException {
		try {
			getRequest().login(login, password);
			currUser = userDao.find(login);
		} catch (ServletException e) {
			return null;
		}
		return "addBook";
	}

	public String logout() throws ServletException {
		currUser = null;
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		if (isAuthenticated())
			getRequest().logout();
		return "logout";
	}

	public boolean isAuthenticated() {
		return getRequest().getUserPrincipal() != null;
	}

	public static HttpServletRequest getRequest() {
		Object request = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		return (HttpServletRequest) request;
	}
}
