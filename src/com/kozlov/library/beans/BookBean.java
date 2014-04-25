package com.kozlov.library.beans;

import java.sql.Date;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Named;

import com.kozlov.library.dao.BookDao;
import com.kozlov.library.enteties.Book;

@Named
@RequestScoped
public class BookBean {
	private Book currBook;
	@Inject
	private BookDao bookDao;

	@PostConstruct
	private void init(){
		currBook = new Book();
	}

	public Book getCurrBook() {
		return currBook;
	}

	public void setCurrBook(Book currBook) {
		this.currBook = currBook;
	}

	public void addBook() {
		bookDao.create(currBook);
	}
	
	public Integer getCurrYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
}
