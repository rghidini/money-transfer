package com.ghidini.tm.dao.interfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IDAO<T,K> {
	
	void insert(T entity);
	T update(T entity);
	void delete(K id);
	T findById(K id);
	List<T> findAll();

}
