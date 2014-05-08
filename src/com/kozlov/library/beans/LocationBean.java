package com.kozlov.library.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.kozlov.library.dao.LocationDao;
import com.kozlov.library.enteties.Location;
import java.io.Serializable;

@Named
@ViewScoped
public class LocationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LocationDao locDao;
	
	private List<Location> locations;
	private Location currLocation;
	
	@PostConstruct
	private void init(){
		currLocation = new Location();
		locations = locDao.findAll();
	}
	public List<Location> getLocations() {
		return locations;
	}

	public Location getCurrLocation() {
		return currLocation;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void setCurrLocation(Location currLocation) {
		this.currLocation = currLocation;
	}
}
