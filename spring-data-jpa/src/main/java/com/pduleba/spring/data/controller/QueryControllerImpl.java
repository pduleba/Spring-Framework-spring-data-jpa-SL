package com.pduleba.spring.data.controller;

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
		utils.show(ownerSerivce.getByFirstNameIsAndLastNameEqualsAndAgeNot("Darek", "J", 0));

		LOG.info("########### WHERE FirstName NOT LIKE %? ###########");
		utils.show(ownerSerivce.getByFirstNameNotLike("%rek"));

		LOG.info("########### WHERE FirstName LIKE %? OR FirstName LIKE ?% OR FirstName LIKE %?% ###########");
		utils.show(ownerSerivce.getByFirstNameStartingWithOrFirstNameEndingWithOrFirstNameContaining("ek", "ek", "ek"));

		LOG.info("########### WHERE Age > ? AND Age <= ? ###########");
		utils.show(ownerSerivce.getByAgeLessThanEqualAndAgeGreaterThan(88, 87));

		LOG.info("########### WHERE Age < ? AND Age > ? AND AGE BETWEEN ? AND ? ###########");
		utils.show(ownerSerivce.getByAgeBeforeAndAgeAfterAndAgeBetween(89, 87, 87, 89));
	}

}
