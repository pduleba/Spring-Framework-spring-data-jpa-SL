package com.pduleba.spring.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pduleba.jpa.model.CarModel;
import com.pduleba.spring.data.dao.CarDao;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDao carDao;
	
	@Override
	public void create(CarModel car) {
		carDao.saveAndFlush(car);
	}

	@Override
	public CarModel getById(long carId) {
		return carDao.getById(carId);
	}

	@Override
	public void update(CarModel car) {
		carDao.saveAndFlush(car);
	}

	@Override
	public void delete(CarModel car) {
		carDao.delete(car);
	}
	
}
