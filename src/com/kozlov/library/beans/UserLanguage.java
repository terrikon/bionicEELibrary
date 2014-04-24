package com.kozlov.library.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;



@ManagedBean(eager = true)
@SessionScoped
public class UserLanguage implements Serializable {

    private static final long serialVersionUID = 1L;
    private Locale locale = FacesContext.getCurrentInstance().getApplication()
            .getDefaultLocale();

    public String[] getCountries() {

        return new String[] { "en", "ru", "uk" };
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLanguage(String lang) {
        locale = new Locale(lang);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

}

