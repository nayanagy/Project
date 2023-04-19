package com.xworkz.user.repository;

import java.time.LocalTime;
import java.util.List;

import com.xworkz.user.entity.ApplicationEntity;

public interface ApplicationRepository {

	List<ApplicationEntity> findAll();

	boolean save(ApplicationEntity entity);

	default Long findByEmail(String email) {
		return null;
	}

	default Long findByMobile(Long mobile) {
		return null;
	}

	default Long findByUser(String userId) {
		return null;
	}

	Long checkDuplicates(String userId, String email, Long mobile);

	default ApplicationEntity findBySignIn(String userId) {
		return null;
	}

	boolean loginCount(String userId, int count);

	default ApplicationEntity reSetPassword(String email) {
		return null;
	}

	boolean update(ApplicationEntity entity);

	boolean updatePassword(String userId, String password, Boolean resetPassword, LocalTime passwordChangeTime);
}
