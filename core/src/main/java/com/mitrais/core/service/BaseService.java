package com.mitrais.core.service;

import java.util.List;

import com.mitrais.core.container.DataContainer;

public interface BaseService<T, ID> {
	DataContainer<List<T>> findAll();

	DataContainer<T> getID(ID id);

	DataContainer<T> save(T data);

	DataContainer<T> update(T data);

	DataContainer<T> delete(ID id);
}
