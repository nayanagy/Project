package com.xworkz.user.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.user.dto.ApplicationDto;

public interface ApplicationService {
	Set<ConstraintViolation<ApplicationDto>> validate(ApplicationDto applicationDto);

	List<ApplicationDto> findAllService();

	default List<ApplicationDto> findByUserAndPassword(String userId, String password) {
		return Collections.emptyList();
	}

	boolean saveService(ApplicationDto applicationDto);

	default Long findByEmail(String email) {
		return null;
	}

	default Long findByMobile(Long mobile) {
		return null;
	}

	default Long findByUser(String userId) {
		return null;
	}
}
