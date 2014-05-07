package com.kozlov.library.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import com.kozlov.library.dao.BookDao;
import com.kozlov.library.dao.OrderItemDao;
import com.kozlov.library.enteties.Book;

@ManagedBean
@ViewScoped
public class BookTableBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private UserBean user;
	@Inject
	private BookDao bookdao;
	@Inject
	private OrderItemDao orderItemDao;
	private List<Book> filteredBooks;

	private List<Book> books;

	private Book selectedBook;

	@PostConstruct
	public void setBooks() {
		books = bookdao.findAll();
		
	}

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

	public List<Book> getFilteredBooks() {
		return filteredBooks;
	}

	public void setFilteredBooks(List<Book> filteredBooks) {
		this.filteredBooks = filteredBooks;
	}

	public List<Book> getBooks() {
		checkBooksAvailability();
		return books;
	}
	
	private void checkBooksAvailability(){
		Iterator<Book> it = books.iterator();
		while(it.hasNext()){
			Book book = it.next();
			if(user.isBookInOrder(book)){
				book.setCanOrder(false);
			}
			if((book.getAvailable()-orderItemDao.amountBookByNameInUse(book))<1)
				book.setCanOrder(false);
		}
	}

}
