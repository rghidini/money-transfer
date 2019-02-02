package com.ghidini.tm.factory;

import java.util.Objects;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFactorySingleton {

	private static EntityManagerFactory factory;
	private EMFactorySingleton() { }

	public static EntityManagerFactory getInstance(){

		if (Objects.isNull(factory)){
			factory = Persistence.createEntityManagerFactory("h2");		
		}
		
		return factory;
	}
}