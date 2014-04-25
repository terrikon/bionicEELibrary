package com.kozlov.library.beans;

import javax.inject.Inject;
import javax.inject.Named;

import com.kozlov.library.dao.BookDao;
import com.kozlov.library.enteties.Book;

@Named
public class BookBean {
	Book currBook;
	@Inject
	BookDao bookDao;

	public BookBean() {
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
}
