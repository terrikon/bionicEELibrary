package com.kozlov.library.dataModels;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.kozlov.library.enteties.Order;

public class OrderDataModel extends ListDataModel<Order> implements SelectableDataModel<Order>, Serializable {    
  
    public OrderDataModel() {  
    }  
  
    public OrderDataModel(List<Order> data) {  
        super(data);  
    }  
      
    @Override  
    public Order getRowData(String rowKey) {  
                 
        List<Order> orders = (List<Order>) getWrappedData();  
          
        for(Order order : orders) {  
            if(order.getOrderId().toString().equals(rowKey))  
                return order;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Order order) {  
        return order.getOrderId();  
    }
    
}   
