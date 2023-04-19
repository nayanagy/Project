package com.xworkz.nayana.service;

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
import org.springframework.stereotype.Service;

import com.xworkz.nayana.dto.JewelleryDTO;
import com.xworkz.nayana.entity.JewelleryEntity;
import com.xworkz.nayana.repository.JewelleryRepository;

@Service
public class JewelleryServiceImpl implements JewelleryService {

	@Autowired
	private JewelleryRepository jewelleryRepository;

	public JewelleryServiceImpl() {
		System.out.println("Created " + this.getClass().getSimpleName());
	}

	@Override
	public Set<ConstraintViolation<JewelleryDTO>> validateAndSave(JewelleryDTO dto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<JewelleryDTO>> violations = validator.validate(dto);
		if (violations != null && !violations.isEmpty()) {
			System.err.println("Violations in dto " + dto);
			return violations;
		} else {
			System.out.println("Violations is not there in dto,can save");
			JewelleryEntity entity = new JewelleryEntity();
			entity.setName(dto.getName());
			entity.setPrice(dto.getPrice());
			entity.setType(dto.getType());
			entity.setColor(dto.getColor());
			entity.setWeight(dto.getWeight());
			boolean saved = this.jewelleryRepository.save(entity);
			System.out.println("Entity data is saved " + saved);
			return Collections.emptySet();
		}
	}

	@Override
	public JewelleryDTO findById(int id) {
		if (id > 0) {
			JewelleryEntity entity = this.jewelleryRepository.findById(id);
			if (entity != null) {
				System.out.println("Entity is found in service for id " + id);
				JewelleryDTO dto = new JewelleryDTO();
				dto.setName(entity.getName());
				dto.setPrice(entity.getPrice());
				dto.setType(entity.getType());
				dto.setColor(entity.getColor());
				dto.setWeight(entity.getWeight());
				dto.setId(entity.getId());
				return dto;
			}
		}
		return JewelleryService.super.findById(id);
	}
	
	
	@Override
	public List<JewelleryDTO> findByName(String name) {
		System.out.println("Running findByName in service " + name);
		if (name != null && !name.isEmpty()) {
			System.out.println("Name is valid calling repo");
			List<JewelleryEntity> entities = this.jewelleryRepository.findByName(name);
			List<JewelleryDTO> listOfDTO = new ArrayList<JewelleryDTO>();
			for (JewelleryEntity entity : entities) {
				JewelleryDTO dto = new JewelleryDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setType(entity.getType());
				dto.setColor(entity.getColor());
				dto.setPrice(entity.getPrice());
				dto.setWeight(entity.getWeight());
				listOfDTO.add(dto);
			}
			System.out.println("Size in dtos " + listOfDTO.size());
			System.out.println("size in entities " + entities.size());
			return listOfDTO;
		} else {
			System.err.println("Name is invalid");
		}
		return JewelleryService.super.findByName(name);
		
	}

	@Override
	public Set<ConstraintViolation<JewelleryDTO>> validateAndUpdate(JewelleryDTO dto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<JewelleryDTO>> violations = validator.validate(dto);
		if (violations != null && !violations.isEmpty()) {
			System.err.println("Violations in dto " + dto);
			return violations;
		} else {
			System.out.println("Violations is not there in dto,can save");
			JewelleryEntity jentity = new JewelleryEntity();
			jentity.setName(dto.getName());
			jentity.setId(dto.getId());
			jentity.setPrice(dto.getPrice());
			jentity.setType(dto.getType());
			jentity.setColor(dto.getColor());
			jentity.setWeight(dto.getWeight());
			boolean update = this.jewelleryRepository.update(jentity);
			System.out.println("Entity data is saved " + update);
			return Collections.emptySet();
		}
	}
	
	
	@Override
	public boolean validateAnddelete(int id) {
		System.out.println("Running Validateanddelete");
		if (id < 0) {
			return false;
		} else {
			boolean deleted=this.jewelleryRepository.delete(id);
			System.out.println("Deleted: "+deleted);
			return deleted;
			
		}
	}
	@Override
	public List<JewelleryDTO> findByNameAndPrice(String name,int price) {
		System.out.println("Running findByName and price in service " + name + price);
		if ((name != null && !name.isEmpty()) && price !=0 ) {
			System.out.println("Name and price is valid calling repo");
			List<JewelleryEntity> entities = this.jewelleryRepository.findByName(name);
			List<JewelleryDTO> listOfDTO = new ArrayList<JewelleryDTO>();
			for (JewelleryEntity entity : entities) {
				JewelleryDTO dto = new JewelleryDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setType(entity.getType());
				dto.setColor(entity.getColor());
				dto.setPrice(entity.getPrice());
				dto.setWeight(entity.getWeight());
				listOfDTO.add(dto);
			}
			System.out.println("Size in dtos " + listOfDTO.size());
			System.out.println("size in entities " + entities.size());
			return listOfDTO;
		} else {
			System.err.println("Name is invalid");
		}
		return JewelleryService.super.findByNameAndPrice(name,price);
		
	}
	
	@Override
	public List<JewelleryDTO> findAll() {
		System.out.println("Running findAll in service ");
			System.out.println("Entity is valid calling repo");
			List<JewelleryEntity> entities = this.jewelleryRepository.findAll();
			List<JewelleryDTO> listOfDTO = new ArrayList<JewelleryDTO>();
			for (JewelleryEntity entity : entities) {
				JewelleryDTO dto = new JewelleryDTO();
				BeanUtils.copyProperties(entity,dto);
				
				listOfDTO.add(dto);
			}
			System.out.println("Size in dtos " + listOfDTO.size());
			System.out.println("size in entities " + entities.size());
			return listOfDTO;
	}
	

}
