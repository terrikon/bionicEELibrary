package com.kozlov.library.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import com.kozlov.library.dao.LocationDao;
import com.kozlov.library.enteties.Location;

@Named
@RequestScoped
public class LocationConverter implements Converter {

	@Inject
	private LocationDao locDao;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (!(value instanceof Location)
				|| ((Location) value).getLocationId() == null) {
			return null;
		}

		return String.valueOf(((Location) value).getPlace());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || !value.matches("\\d+")) {
			return null;
		}

		Location location = locDao.findByLocation(value.toString());

		if (location == null) {
			throw new ConverterException(new FacesMessage(
					"Unknown location: " + value));
		}

		return location;
	}

}
