package com.xworkz.nayana.repository;

import java.util.Collections;
import java.util.List;

import com.xworkz.nayana.entity.JewelleryEntity;



public interface JewelleryRepository {
	boolean save(JewelleryEntity entity);
	boolean update(JewelleryEntity entity);

	default JewelleryEntity findById(int id) {
		return null;
	}

	default List<JewelleryEntity> findByName(String name) {
		return Collections.emptyList();
	}
	
	default List<JewelleryEntity> findByNameAndPrice(String name,int price) {
		return Collections.emptyList();
	}
	
	default List<JewelleryEntity> findAll() {
		return Collections.emptyList();
	}
	
	boolean delete(int id);
}
