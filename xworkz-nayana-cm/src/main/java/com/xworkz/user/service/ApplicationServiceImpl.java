package com.xworkz.user.service;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xworkz.user.dto.ApplicationDto;
import com.xworkz.user.entity.ApplicationEntity;
import com.xworkz.user.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	public ApplicationRepository applicationRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public ApplicationServiceImpl() {
		log.info("Running ApplicationServiceImpl");
	}

	
	public Set<ConstraintViolation<ApplicationDto>> validate(ApplicationDto applicationDto) {
		log.info("Running validate ");
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<ApplicationDto>> violation = validator.validate(applicationDto);
		if (!violation.isEmpty()) {
			return violation;
		} else {
//		ApplicationEntity entity = new ApplicationEntity();
//		entity.setPassword(passwordEncoder.encode(applicationDto.getConfirmPassword()));

			applicationDto.setPassword(passwordEncoder.encode(applicationDto.getConfirmPassword()));
			return Collections.emptySet();
		}

	}

	@Override
	public List<ApplicationDto> findAllService() {
		log.info("Running findAll");
		List<ApplicationEntity> entity = this.applicationRepository.findAll();
		List<ApplicationDto> dtos = new ArrayList();
		for (ApplicationEntity applicationEntity : entity) {
			ApplicationDto dto = new ApplicationDto();
			dto.setUserId(applicationEntity.getUserId());
			dto.setEmail(applicationEntity.getEmail());
			dto.setMobile(applicationEntity.getMobile());
			dto.setPassword(applicationEntity.getPassword());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public boolean saveService(ApplicationDto applicationDto) {
		log.info("Running saveService");
		ApplicationEntity entity = new ApplicationEntity();
		BeanUtils.copyProperties(applicationDto, entity);
		//entity.setPassword(passwordEncoder.encode(applicationDto.getConfirmPassword()));
		this.applicationRepository.save(entity);
		return true;
	}

	@Override
	public List<ApplicationDto> findByUserAndPassword(String userId, String password) {
		log.info("Running findByUserAndPassword in service " + userId + password);
		if (userId != null && !userId.isEmpty() && password != null && !password.isEmpty()) {
			log.info("userId and password is valid calling repo");
			List<ApplicationEntity> entities = this.applicationRepository.findByUserAndPassword(userId, password);
			List<ApplicationDto> listOfDTO = new ArrayList<ApplicationDto>();
			for (ApplicationEntity entity : entities) {
				ApplicationDto dto = new ApplicationDto();
				BeanUtils.copyProperties(entity, dto);
				listOfDTO.add(dto);
			}
			log.info("Size in dtos " + listOfDTO.size());
			log.info("size in entities " + entities.size());
			return listOfDTO;
		} else {
			log.info("userId and password is invalid");
		}
		return ApplicationService.super.findByUserAndPassword(userId, password);
	}

	@Override
	public Long findByEmail(String email) {
		Long emailcount = this.applicationRepository.findByEmail(email);
		log.error("Find  by Email");
		return emailcount;
	}

	@Override
	public Long findByMobile(Long mobile) {
		Long mobilecount = this.applicationRepository.findByMobile(mobile);
		return mobilecount;
	}

	@Override
	public Long findByUser(String userId) {
		Long userCount = this.applicationRepository.findByUser(userId);
		return userCount;
	}


	

}
