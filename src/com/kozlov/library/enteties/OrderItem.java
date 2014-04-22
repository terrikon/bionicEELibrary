package com.kozlov.library.enteties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderItemId;
	@ManyToOne
	@JoinColumn(name = "bookId")
	private Book book;
	@ManyToOne
	@JoinColumn(name = "locationId")
	private Location location;

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public Book getBook() {
		return book;
	}

	public Location getLocation() {
		return location;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
