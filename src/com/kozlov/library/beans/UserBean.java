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

	public String login() throws IOException {
		try {
			getRequest().login(currUser.getLogin(), currUser.getPass());
			currUser = userDao.find(currUser.getLogin());
		} catch (ServletException e) {
			return null;
		}
		if (currUser.getUserType().getType().equalsIgnoreCase("librarian")){
		return "/faces/pages/librarian/addBook";
		}else {
			return null;
		}
	}

	public String logout() throws ServletException {
		currUser = null;
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		if (isAuthenticated())
			getRequest().logout();
		return "/faces/pages/guest/indexGuest";
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
