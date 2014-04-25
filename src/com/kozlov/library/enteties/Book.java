package com.kozlov.library.enteties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="Book")
@NamedQueries({
//@NamedQuery(name="Product.countAll", query="SELECT count(p) FROM Product p"),		
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")})
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bookId;
	private String title;
	private String author;
	private Integer year;
	private Integer available = 1;

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
}
