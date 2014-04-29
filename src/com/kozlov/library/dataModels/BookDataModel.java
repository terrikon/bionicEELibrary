package com.kozlov.library.dataModels;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.kozlov.library.enteties.Book;

public class BookDataModel extends ListDataModel<Book> implements SelectableDataModel<Book>, Serializable {    
  
    public BookDataModel() {  
    }  
  
    public BookDataModel(List<Book> data) {  
        super(data);  
    }  
      
    @Override  
    public Book getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
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


