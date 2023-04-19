package com.xworkz.user.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.user.entity.ApplicationEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ApplicationRepositoryImpl implements ApplicationRepository {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public ApplicationRepositoryImpl() {
		log.info("Running ApplicationRepositoryImpl");
	}

	@Override
	public List<ApplicationEntity> findAll() {
		log.info("Running findUserEmailMobile");
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("findAll");
		List<ApplicationEntity> entities = query.getResultList();
		return entities;
	}

	@Override
	public boolean save(ApplicationEntity applicationEntity) {
		log.info("Running save");
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(applicationEntity);
		transaction.commit();
		entityManager.close();
		return true;
	}

	@Override
	public List<ApplicationEntity> findByUserAndPassword(String userId, String password) {
		log.info("Running findByUserAndPassword in repository ");
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("findByUserIdAndPassword");
			query.setParameter("us", userId);
			query.setParameter("pas", password);
			log.info("query : " + query);
			List<ApplicationEntity> list = query.getResultList();
			log.info("Total list found in repo " + list.size());
			return list;
		} finally {
			manager.close();
			log.info("Released the connection");
		}
	}

	@Override
	public Long findByEmail(String email) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("emailId");
			query.setParameter("emailBy", email);
			Object object = query.getSingleResult();
			Long value = (Long) object;
			System.out.println(value);
			return value;

		} finally {
			em.close();
		}

	}

	@Override
	public Long findByUser(String userId) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("userId");
			query.setParameter("userBy", userId);
			Object object = query.getSingleResult();
			Long value = (Long) object;
			System.out.println(value);
			return value;

		} finally {
			em.close();
		}
	}

	@Override
	public Long findByMobile(Long mobile) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("mobileId");
			query.setParameter("mobileBy", mobile);
			Object object = query.getSingleResult();
			Long value = (Long) object;
			System.out.println(value);
			return value;

		} finally {
			em.close();
		}
	}
}
