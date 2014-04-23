package com.kozlov.library.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.kozlov.library.enteties.Book;

@Stateless
public class BookDao extends GeneralDao<Book, Integer> {
	public BookDao() {
		super(Book.class);
	}
	public List<Book> findAll() {

		TypedQuery<Book> query = em.createNamedQuery("Book.findAll",Book.class);
		List<Book> results = query.getResultList();
		return results;
	}
}
