package com.xworkz.user.repository;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

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
		log.info("Created " + this.getClass().getSimpleName());
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
	public Long findByUser(String userId) {
		log.info("Running findByUser");
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("userId");
			query.setParameter("userBy", userId);
			Object object = query.getSingleResult();
			Long value = (Long) object;
			log.info("Value " + value);
			return value;
		} finally {
			em.close();
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
			log.info("Value " + value);
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
			log.info("Value " + value);
			return value;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean save(ApplicationEntity entity) {
		log.info("Running save in Repository");
		EntityManager em = this.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(entity);
		et.commit();
		em.close();
		return true;
	}

	@Override
	public Long checkDuplicates(String userId, String email, Long mobile) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("checkdupl");
			query.setParameter("userId", userId);
			query.setParameter("userEmail", email);
			query.setParameter("userMobile", mobile);
			Object obj = query.getSingleResult();
			Long count = (Long) obj;
			log.info("Count " + count);
			log.info("Duplicates value size" + count);
			return count;
		} finally {
			manager.close();
		}
	}

	@Override
	public ApplicationEntity findBySignIn(String userId) {
		log.info("Running findByUserAndPassword in repository ");
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("findBySignIn");
			query.setParameter("us", userId);
			log.info("query : " + query);
			Object obj = query.getSingleResult();
			ApplicationEntity entity = (ApplicationEntity) obj;
			log.info("Count " + entity);
			log.info("Duplicates value size" + entity);
			return entity;
		} finally {
			manager.close();
			log.info("Released the connection");
		}
	}

	@Override
	public boolean loginCount(String userId, int count) {
		log.info("count " + count);
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateLoginCount");
			query.setParameter("user", userId);
			query.setParameter("count", count);
			query.executeUpdate();
			transaction.commit();
			return true;
		} finally {
			manager.close();
		}
	}

	@Override
	public ApplicationEntity reSetPassword(String email) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("resetEmail");
			query.setParameter("email", email);
			Object object = query.getSingleResult();
			ApplicationEntity entity = (ApplicationEntity) object;
			log.info("" + entity);
			return entity;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean update(ApplicationEntity entity) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.merge(entity);
			et.commit();
			return true;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean updatePassword(String userId, String password, Boolean resetPassword, LocalTime passwordChangeTime) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			et.begin();
			Query query = em.createNamedQuery("updatePassword");
			query.setParameter("usr", userId);
			query.setParameter("userPass", password);
			query.setParameter("resetPass", resetPassword);
			query.setParameter("timer", passwordChangeTime);
			query.executeUpdate();
			et.commit();
			return true;
		} finally {
			em.close();
		}
	}

}
