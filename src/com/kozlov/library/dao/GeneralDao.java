package com.kozlov.library.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GeneralDao<T, PK> implements Dao<T, PK> {
	private Class<T> type;

	public GeneralDao(Class<T> type) {
		this.type = type;
	}

	public Class<T> getType() {
		return type;
	}

	@PersistenceContext
	EntityManager em;

	@Override
	public void create(T newInstance) {

		em.persist(newInstance);

	}

	@Override
	public void delete(T entety) {

		em.remove(em.contains(entety) ? entety : em.merge(entety));

	}

	@Override
	public T read(PK id) {

		T result;
		result = em.find(type, id);

		return result;
	}

	@Override
	public void update(T entety) {
		em.merge(entety);

	}
}
