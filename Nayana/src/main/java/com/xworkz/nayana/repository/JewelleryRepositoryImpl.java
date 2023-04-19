package com.xworkz.nayana.repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.nayana.entity.JewelleryEntity;



@Repository
public class JewelleryRepositoryImpl implements JewelleryRepository {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public JewelleryRepositoryImpl() {
		System.out.println("Created " + this.getClass().getSimpleName());
	}

	@Override
	public boolean save(JewelleryEntity entity) {
		System.out.println("Running save in Repository");
		EntityManager em = this.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(entity);
		et.commit();
		em.close();
		return true;
	}

	@Override
	public JewelleryEntity findById(int id) {
		System.out.println("find by id in repo " + id);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		JewelleryEntity fromDb = entityManager.find(JewelleryEntity.class, id);
		entityManager.close();
		return fromDb;
	}
	
	
	@Override
	public List<JewelleryEntity> findByName(String name) {
		System.out.println("Running findByName in repository " + name);
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("findByName");
			query.setParameter("nam", name);
			System.out.println("query : " + query);
			List<JewelleryEntity> list = query.getResultList();
			System.out.println("Total list found in repo " + list.size());
			return list;
		} finally {
			manager.close();
			System.out.println("Released the connection");
		}
	}

	@Override
	public boolean update(JewelleryEntity entity) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(entity);
			transaction.commit();
			return true;
		}finally {
			manager.close();
		}
		
	}
	
	@Override
	public boolean delete(int id) {
		System.out.println("Running delete");
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		JewelleryEntity del=entityManager.find(JewelleryEntity.class, id);
		
		entityTransaction.begin();
		entityManager.remove(del);
		entityTransaction.commit();
		entityManager.close();

		return true;	

	}
	
	@Override
	public List<JewelleryEntity> findByNameAndPrice(String name,int price) {
		System.out.println("Running findByNameAndPrice in repository " + name +price);
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("findByNameAndPrice");
			query.setParameter("nam", name);
			query.setParameter("pri", price);
			System.out.println("query : " + query);
			List<JewelleryEntity> list = query.getResultList();
			System.out.println("Total list found in repo " + list.size());
			return list;
		} finally {
			manager.close();
			System.out.println("Released the connection");
		}
	}
	
	
	@Override
	public List<JewelleryEntity> findAll() {
		System.out.println("Running findAll in repository ");
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("findAll");
			System.out.println("query : " + query);
			List<JewelleryEntity> list = query.getResultList();
			System.out.println("Total list found in repo " + list.size());
			return list;
		} finally {
			manager.close();
			System.out.println("Released the connection");
		}
	}
	

}
