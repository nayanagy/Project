package com.xworkz.nayana.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.nayana.dto.JewelleryDTO;



public interface JewelleryService {
	Set<ConstraintViolation<JewelleryDTO>> validateAndSave(JewelleryDTO dto);

	default JewelleryDTO findById(int id) {
		return null;
	}
	
	default List<JewelleryDTO> findByName(String name) {
		return Collections.emptyList();
	}
	
	default List<JewelleryDTO> findByNameAndPrice(String name,int price) {
		return Collections.emptyList();
	}
	
	default List<JewelleryDTO> findAll() {
		return Collections.emptyList();
	}
	Set<ConstraintViolation<JewelleryDTO>> validateAndUpdate(JewelleryDTO dto);
	
	default boolean validateAnddelete(int id) {
		return false;
	}
}