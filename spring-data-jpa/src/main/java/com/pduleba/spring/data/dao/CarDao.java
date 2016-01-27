package com.pduleba.spring.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.jpa.model.CarModel;

@Repository
@Transactional
public interface CarDao extends JpaRepository<CarModel, Long> {

	CarModel getById(Long carId);

	void delete(CarModel car);
	
}
