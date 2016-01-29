package com.pduleba.spring.data.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pduleba.jpa.model.CarModel;
import com.pduleba.jpa.model.OwnerModel;
import com.pduleba.spring.data.services.CarService;
import com.pduleba.spring.data.services.OwnerService;
import com.pduleba.spring.data.services.UtilityService;
import com.pduleba.spring.data.services.UtilityService.Mode;

@Component
public class DBControllerImpl implements DBController {

	public static final Logger LOG = LoggerFactory.getLogger(DBControllerImpl.class);
	
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private CarService carService;
	@Autowired
	private UtilityService utils;
	
	@Value(value="${application.remove.enabled}")
	private boolean deleteEnabled = true;
	
	@Override
	public void processCar() {
		CarModel car = utils.getCar();
		
		carService.create(car);
		car = carService.getById(car.getId());
		car.setName(Thread.currentThread().getName());
		carService.update(car);
		
		LOG.info("The end!");
	}
	

	@Override
	public void createDB() {
//		if (databaseExists()) {
//			LOG.info("Database already exists");
//		} else {
			LOG.info("------------");
			Long ownerId = create();
			LOG.info("------------");
			OwnerModel persisted = getById(ownerId);
			LOG.info("------------");
			update(persisted, Thread.currentThread().getName());
			LOG.info("------------");
			delete(persisted);
			LOG.info("------------");
//		}
	}
	
	private boolean databaseExists() {
		return 0L != ownerService.count();
	}

	private Long create() {
		List<OwnerModel> owners = utils.getData();
		utils.show(owners, Mode.CREATE);
		ownerService.createAll(owners);
		
		return owners.get(0).getId();
	}

	private OwnerModel getById(long ownerId) {
		OwnerModel owner = ownerService.getById(ownerId);
		utils.show(owner, Mode.READ);
		
		return owner;
	}

	private void update(OwnerModel owner, String newName) {
		owner.setFirstName(newName);
		utils.show(owner, Mode.UPDATE);
		ownerService.update(owner);
	}

	private void delete(OwnerModel owner) {
		if (deleteEnabled) {
			ownerService.delete(owner);
			OwnerModel deleted = ownerService.getById(owner.getId());
			utils.show(deleted, Mode.DELETE);
		} else {
			LOG.warn("Delete feature disabled!");
		}
	}
}
