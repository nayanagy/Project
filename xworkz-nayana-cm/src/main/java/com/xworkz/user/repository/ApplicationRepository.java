package com.xworkz.user.repository;

import java.util.Collections;
import java.util.List;

import com.xworkz.user.entity.ApplicationEntity;

public interface ApplicationRepository {
	List<ApplicationEntity> findAll();

	default List<ApplicationEntity> findByUserAndPassword(String userId, String password) {
		return Collections.emptyList();
	}

	boolean save(ApplicationEntity applicationEntity);
	
	default Long findByUser(String userId) {
		return null;
	}

	default Long findByEmail(String email) {
		return null;
	}

	default Long findByMobile(Long mobile) {
		return null;
	}
}
