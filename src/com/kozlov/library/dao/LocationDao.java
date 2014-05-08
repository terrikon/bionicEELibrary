package com.kozlov.library.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.kozlov.library.enteties.Location;
import com.kozlov.library.enteties.User;

@Stateless
public class LocationDao extends GeneralDao<Location, Integer> {
	public LocationDao() {
		super(Location.class);
	}

	public List<Location> findAll() {

		TypedQuery<Location> query = em.createNamedQuery("Location.findAll",Location.class);
		List<Location> results = query.getResultList();
		return results;
	}
	public Location findByLocation(String location) {
		TypedQuery<Location> query = em.createNamedQuery("Location.readByPlace",
				Location.class);
		query.setParameter("location",location);
		Location result = query.getSingleResult();
		return result;
	}
}
