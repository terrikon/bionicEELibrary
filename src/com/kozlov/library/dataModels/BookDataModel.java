package com.kozlov.library.dataModels;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.kozlov.library.enteties.Book;
import com.kozlov.library.enteties.OrderItem;

public class BookDataModel extends ListDataModel<Book> implements SelectableDataModel<Book>, Serializable {    
  
    public BookDataModel() {  
    }  
  
    public BookDataModel(List<Book> data) {  
        super(data);  
    }  
      
    @Override  
    public Book getRowData(String rowKey) {  
                 
        List<Book> books = (List<Book>) getWrappedData();  
          
        for(Book book : books) {  
            if(book.getBookId().toString().equals(rowKey))  
                return book;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Book book) {  
        return book.getBookId();  
    }
    
}   


