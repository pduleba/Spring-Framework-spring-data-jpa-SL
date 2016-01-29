package com.pduleba.spring.data.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pduleba.spring.data.services.OwnerService;
import com.pduleba.spring.data.services.UtilityService;

import lombok.Data;

@Component
public @Data class QueryControllerImpl implements QueryController {

	public static final Logger LOG = LoggerFactory.getLogger(QueryControllerImpl.class);

	@Autowired
	private OwnerService ownerSerivce;
	@Autowired
	private UtilityService utils;

	@Override
	public void executeQueries() {
		LOG.info("----------- Execute Query DSL -----------");
		LOG.info("########### WHERE FirstName = ? ###########");
		utils.show(ownerSerivce.getByFirstName("Darek"));

		LOG.info("########### WHERE FirstName LIKE ?% OR LastName LIKE %? ###########");
		utils.show(ownerSerivce.getByFirstNameLikeOrLastNameLike("Darek%", "%J"));

		LOG.info("########### WHERE FirstName = ? AND LastName = ? AND Age <> ? ###########");
		utils.show(ownerSerivce.getByFirstNameIsAndLastNameEqualsAndAgeNot("Darek", "D", 0));

		LOG.info("########### WHERE FirstName NOT LIKE %? ###########");
		utils.show(ownerSerivce.getByFirstNameNotLike("%rek"));

		LOG.info("########### WHERE FirstName LIKE %? OR FirstName LIKE ?% OR FirstName LIKE %?% ###########");
		utils.show(ownerSerivce.getByFirstNameStartingWithOrFirstNameEndingWithOrFirstNameContaining("ek", "ek", "ek"));

		LOG.info("########### WHERE Age > ? AND Age <= ? ###########");
		utils.show(ownerSerivce.getByAgeLessThanEqualAndAgeGreaterThan(82, 80));

		LOG.info("########### WHERE Age < ? AND Age > ? AND AGE BETWEEN ? AND ? ###########");
		utils.show(ownerSerivce.getByAgeBeforeAndAgeAfterAndAgeBetween(82, 80, 80, 82));
		
		LOG.info("########### WHERE Active = True ###########");
		utils.show(ownerSerivce.getByActiveTrue());
		
		LOG.info("########### WHERE Age IS NOT NULL OR Active IS NULL ###########");
		utils.show(ownerSerivce.getByAgeIsNotNullOrActiveIsNull());
		
		LOG.info("########### WHERE Age IN (?) AND Active NOT IN (?) ###########");
		utils.show(ownerSerivce.getByAgeInAndActiveNotIn(Arrays.asList(81, 39), Arrays.asList(Boolean.TRUE)));
		
		LOG.info("########### ALIASING : WHERE cars.Name IN (?) ###########");
		utils.show(ownerSerivce.getByCarsNameIn(Arrays.asList("Audi-2-2")));
		
	}

}
