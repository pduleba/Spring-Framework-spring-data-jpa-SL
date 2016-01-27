package com.pduleba.spring.data.services;

import com.pduleba.jpa.model.CarModel;

public interface CarService {

	void create(CarModel car);

	CarModel getById(long carId);

	void update(CarModel car);

	void delete(CarModel car);

}
