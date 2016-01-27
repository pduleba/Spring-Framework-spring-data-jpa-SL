package com.pduleba.spring.data.services;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.Clob;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

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
	public void show(Collection<?> entities) {
		show(entities, Mode.READ);
	}
	
	@Override
	public void show(Collection<?> entities, Mode mode) {
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
	public List<OwnerModel> getData() {
		Clob spec = getClob(env.getProperty(KEY_SPEC_FILE_CLASSPATH_LOCATION));
		Blob image = getBlob(env.getProperty(KEY_IMAGE_FILE_CLASSPATH_LOCATION));
		
		List<OwnerModel> owners = new LinkedList<>();
		String[][] persons = {{"Adam","A"}, {"Jola","J"}, {"Zbyszek","Z"}, {"Filip","F"}, {"Darek","D"}, {"Bartek","B"}};
		OwnerModel owner;
		CarModel car;
		Integer age, wheels;
		int userIndex = 0;
		String carName;
		
		for (String[] person : persons) {
			age = Integer.valueOf((int)(Math.random() * 99));
			owner = new OwnerModel(person[0], person[1], age, getRandomType());
			int numberOfCars = Integer.valueOf((int)(Math.random() * 6));
			userIndex++;
			
			for (int i = 1; i <= numberOfCars; i++) {
				wheels = Integer.valueOf((int)(Math.random() * 5));
				carName = "Audi-" + userIndex + "-"+ i;
				car = new CarModel(carName, wheels, spec, image);
				owner.addCar(car);
			}
			
			owners.add(owner);
		}
		
		return owners;
	}

	private OwnerType getRandomType() {
		return OWNER_TYPES[generator.nextInt(OWNER_TYPES.length)];
	}

	private Blob getBlob(String classpathLocation) {
		ClassPathResource imageFile = new ClassPathResource(classpathLocation);
		
		Blob resource = null;
		if (imageFile.exists()) {
			
			try {
				try (InputStream inputStream = imageFile.getInputStream()) {
					resource = new SerialBlob(StreamUtils.copyToByteArray(inputStream));
				}
			} catch (Exception e) {
				LOG.error(MessageFormat.format("Unable to load resource from {0}", classpathLocation), e);
			}
		} else {
			LOG.warn("Resource do not exist on {}", classpathLocation);
		}
		return resource;
	}
	
	private Clob getClob(String classpathLocation) {
		ClassPathResource imageFile = new ClassPathResource(classpathLocation);
		
		Clob resource = null;
		if (imageFile.exists()) {
			
			try {
				try (InputStream inputStream = imageFile.getInputStream()) {
					resource = new SerialClob(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")).toCharArray());
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
