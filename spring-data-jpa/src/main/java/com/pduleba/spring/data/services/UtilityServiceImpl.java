package com.pduleba.spring.data.services;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.pduleba.configuration.ApplicationPropertiesConfiguration;
import com.pduleba.jpa.model.CarModel;
import com.pduleba.jpa.model.OwnerModel;
import com.pduleba.jpa.model.OwnerType;

@Component
class UtilityServiceImpl implements UtilityService, ApplicationPropertiesConfiguration {

	public static final Logger LOG = LoggerFactory.getLogger(UtilityServiceImpl.class);
	
	final private OwnerType[] OWNER_TYPES =  OwnerType.values();
	
	@Autowired
	private Environment env;
	
	private Random generator = new Random();

	@Override
	public void show(Object entity) {
		show(entity, Mode.READ);
	}
	
	@Override
	public void show(Object entity, Mode mode) {
		if (Objects.isNull(entity)) {
			LOG.info("{} :: ENTITY NOT FOUND", mode);
		} else if (BooleanUtils.isFalse(Hibernate.isInitialized(entity))) {
			LOG.info("{} :: ENTITY NOT INITIALIZED", entity);
		} else {
			LOG.info("{} :: {}", mode, entity);
		}
	}

	@Override
	public void show(List<OwnerModel> entities) {
		show(entities, Mode.READ);
	}
	
	@Override
	public void show(List<OwnerModel> entities, Mode mode) {
		if (Objects.isNull(entities)) {
			LOG.info("{} :: COLLECTION NOT FOUND", mode);
		} else if (BooleanUtils.isFalse(Hibernate.isInitialized(entities))) {
			LOG.info("{} :: COLLECTION NOT INITIALIZED", entities);
		} else {
			for (Object entity : entities) {
				show(entity, mode);
			}
		}
	}

	@Override
	public void show(int updateOrCount) {
		LOG.info("{} :: {}", Mode.UPDATE_OR_COUNT, updateOrCount);
	}
	
	@Override
	public CarModel getCar() {
		String spec = getClob();
		byte[] image = getBlob();
		
		return new CarModel(getCarName(0, 0), getWheelsNumber(), spec, image);
	}
	
	@Override
	public List<OwnerModel> getData() {
		String spec = getClob();
		byte[] image = getBlob();
		
		List<OwnerModel> owners = new LinkedList<>();
		String[][] persons = {{"Adam","A"}, {"Jola","J"}, {"Zbyszek","Z"}, {"Filip","F"}, {"Darek","D"}, {"Bartek","B"}};
		OwnerModel owner;
		CarModel car;
		Integer age;
		Boolean active;
		int userIndex = 0;
		
		for (String[] person : persons) {
			age = Integer.valueOf((int)(Math.random() * 99));
			active = Math.random() > 0.5 ? Boolean.FALSE : Boolean.TRUE;
			owner = new OwnerModel(person[0], person[1], age, active, getRandomType());
			int numberOfCars = Integer.valueOf((int)(Math.random() * 6));
			userIndex++;
			
			for (int i = 1; i <= numberOfCars; i++) {
				car = new CarModel(getCarName(userIndex, i), getWheelsNumber(), spec, image);
				owner.addCar(car);
			}
			
			owners.add(owner);
		}
		
		return owners;
	}

	private Integer getWheelsNumber() {
		return Integer.valueOf((int)(Math.random() * 5));
	}

	private String getCarName(int userIndex, int i) {
		return "Audi-" + userIndex + "-"+ i;
	}

	private OwnerType getRandomType() {
		return OWNER_TYPES[generator.nextInt(OWNER_TYPES.length)];
	}

	private byte[] getBlob() {
		String classpathLocation = env.getProperty(KEY_IMAGE_FILE_CLASSPATH_LOCATION);
		ClassPathResource imageFile = new ClassPathResource(classpathLocation);
		
		byte[] resource = null;
		if (imageFile.exists()) {
			
			try {
				try (InputStream inputStream = imageFile.getInputStream()) {
					resource = StreamUtils.copyToByteArray(inputStream);
				}
			} catch (Exception e) {
				LOG.error(MessageFormat.format("Unable to load resource from {0}", classpathLocation), e);
			}
		} else {
			LOG.warn("Resource do not exist on {}", classpathLocation);
		}
		return resource;
	}
	
	private String getClob() {
		String classpathLocation = env.getProperty(KEY_SPEC_FILE_CLASSPATH_LOCATION);
		ClassPathResource imageFile = new ClassPathResource(classpathLocation);
		
		String resource = null;
		if (imageFile.exists()) {
			
			try {
				try (InputStream inputStream = imageFile.getInputStream()) {
					resource = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
				}
			} catch (Exception e) {
				LOG.error(MessageFormat.format("Unable to load resource from from {0}", classpathLocation), e);
			}
		} else {
			LOG.warn("Resource do not exist on {}", classpathLocation);
		}
		return resource;
	}
}
