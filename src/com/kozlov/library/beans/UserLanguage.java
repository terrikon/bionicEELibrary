package com.kozlov.library.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;



@ManagedBean(eager=true)
@SessionScoped
public class UserLanguage implements Serializable {

   private static final long serialVersionUID = 1L;
   private String locale;

   private static Map<String,Object> countries;
   
   static{
      countries = new LinkedHashMap<String,Object>();
      countries.put("English", new Locale("en"));
      countries.put("Russian", new Locale("ru"));
      countries.put("Ukrainian", new Locale("uk"));
   }

   public Map<String, Object> getCountries() {
      return countries;
   }

   public String getLocale() {
      return locale;
   }

   public void setLocale(String locale) {
      this.locale = locale;
   }

   //value change event listener
   public void localeChanged(ValueChangeEvent e){
      String newLocaleValue = e.getNewValue().toString();
      for (Map.Entry<String, Object> entry : countries.entrySet()) {
         if(entry.getValue().toString().equals(newLocaleValue)){
            FacesContext.getCurrentInstance()
               .getViewRoot().setLocale((Locale)entry.getValue());         
         }
      }
   }
}

