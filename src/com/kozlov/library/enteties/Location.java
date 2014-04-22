package com.kozlov.library.enteties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer locationId;
	private String place;

	public Integer getLocationId() {
		return locationId;
	}

	public String getPlace() {
		return place;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
