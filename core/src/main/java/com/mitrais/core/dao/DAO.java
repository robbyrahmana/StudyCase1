package com.mitrais.core.dao;

import java.util.List;

import com.mitrais.core.datasource.DatabaseHandler;

public interface DAO<T, ID> {
	List<T> findAll();

	T getID(ID id);

	boolean save(T data);

	boolean update(T data);

	boolean delete(ID id);
	
	default DatabaseHandler getDataHandler() {
		return DatabaseHandler.getInstance();
	}
}
