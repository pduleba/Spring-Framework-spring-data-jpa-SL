package com.pduleba.spring.data.dao;

import com.pduleba.jpa.model.CarModel;

public interface CarDao {

	void create(CarModel car);

	CarModel read(long carId);

	void update(CarModel car);

	void delete(CarModel car);
	
}
