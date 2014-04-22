package com.kozlov.library.enteties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String name;
	private String surname;
	private String login;
	private String pass;

	@ManyToOne
	@JoinColumn(name = "userTypeId")
	private UserType userType;

	public Integer getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
