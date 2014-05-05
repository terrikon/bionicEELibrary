package com.kozlov.library.dataModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.kozlov.library.dao.BookDao;
import com.kozlov.library.enteties.Book;

public class LazyBookDataModel extends LazyDataModel<Book> {
	@Inject
	private BookDao bookDao;
	private List<Book> datasource;

	public LazyBookDataModel(List<Book> datasource) {
		this.datasource = datasource;
	}

	@Override
	public Book getRowData(String rowKey) {
		Book book = bookDao.read(Integer.parseInt(rowKey));
		return book;
	}

	@Override
	public Object getRowKey(Book book) {
		return book.getBookId();
	}

	@Override
	public List<Book> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<Book> data = new ArrayList<Book>();

		// filter
		for (Book book : datasource) {
			boolean match = true;

			for (Iterator<String> it = filters.keySet().iterator(); it
					.hasNext();) {
				try {
					String filterProperty = it.next();
					String filterValue = filters.get(filterProperty);
					String fieldValue = String.valueOf(book.getClass()
							.getField(filterProperty).get(book));

					if (filterValue == null
							|| fieldValue.startsWith(filterValue)) {
						match = true;
					} else {
						match = false;
						break;
					}
				} catch (Exception e) {
					match = false;
				}
			}

			if (match) {
				data.add(book);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new LazySorter(sortField, sortOrder));
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}
}
