package com.kozlov.library.dataModels;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.kozlov.library.enteties.Book;

public class LazySorter implements Comparator<Book> {

	private String sortField;

	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Book book1, Book book2) {
		try {
			Object value1 = Book.class.getField(this.sortField).get(book1);
			Object value2 = Book.class.getField(this.sortField).get(book2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}