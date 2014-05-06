package com.kozlov.library.enteties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Book")
@NamedQueries({
// @NamedQuery(name="Product.countAll", query="SELECT count(p) FROM Product p"),
@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b") })
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	private String title;
	private String author;
	private Integer year;
	private Integer available = 1;
	@Transient
	private boolean canOrder = true;

	public boolean isCanOrder() {
		return canOrder;
	}

	public void setCanOrder(boolean canOrder) {
		this.canOrder = canOrder;
	}

	public Integer getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public boolean equals(Book book) {
		if (book == null)
			return false;

		if (this.bookId == book.bookId)
			return true;
		return false;

	}
}
