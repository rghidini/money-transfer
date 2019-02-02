package com.ghidini.tm.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import org.jvnet.hk2.annotations.Service;

import com.ghidini.tm.dao.interfaces.IDAO;
import com.ghidini.tm.factory.EMFactorySingleton;

@Service
public class DAO<T,K> implements IDAO<T, K> {

	private Class<T> classe;

	@SuppressWarnings("unchecked")
	public DAO() {
		classe = 
				(Class<T>) ((ParameterizedType)
						getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public void insert(T entity) {
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		if (em.getTransaction().isActive())
			em.getTransaction().rollback();
		em.close();
	}

	@Override
	public T update(T entity) {
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		T merge;
		em.getTransaction().begin();
		merge = em.merge(entity);
		em.getTransaction().commit();
		if (em.getTransaction().isActive())
			em.getTransaction().rollback();
		em.close();
		return merge;
	}

	@Override
	public void delete(K id) {
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		T entity = findById(id);
		em.getTransaction().begin();
		em.remove(em.getReference(entity.getClass(), id));
		em.getTransaction().commit();
		if (em.getTransaction().isActive())
			em.getTransaction().rollback();
		em.close();
	}

	@Override
	public T findById(K id) {
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		T find;
		find = em.find(classe, id);
		em.clear();
		return find;
	}

	@Override
	public List<T> findAll(){
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		List<T> list = null;
		list = em.createQuery("from " + classe.getName(), classe).getResultList();
		em.close();
		return list;
	}

}
