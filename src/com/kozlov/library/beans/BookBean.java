package com.kozlov.library.beans;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.kozlov.library.dao.BookDao;
import com.kozlov.library.enteties.Book;
import com.kozlov.library.enteties.OrderItem;

@Named
@RequestScoped
public class BookBean {
	private Book currBook;
	@Inject
	private BookDao bookDao;
	@Inject
	private UserBean user;
	@PostConstruct
	private void init() {
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

	public Integer getCurrYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public boolean isBookInOrder(){
		boolean result=false;
		for(OrderItem oi:user.getCurrOrder().getOrderItems()){
		  if (oi.getBook().getTitle().equals(currBook.getTitle())){
			  result=true;
		  }
	  }
		return result;
	}

}
