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
		LOG.info("----------- Usage of @Query -----------");
		LOG.info("########### WHERE FirstName = ? AND LastName LIKE ? AND Age <> ? (name based parameters) ###########");
		utils.show(ownerSerivce.findByFirstNameLastNameAndAgeNameBased("Jola", "J", Integer.valueOf(0)));

		LOG.info("########### WHERE FirstName = ? AND LastName LIKE ? AND Age <> ? (order based parameters) ###########");
		utils.show(ownerSerivce.findByFirstNameLastNameAndAgeOrderBased("Jola", "J", Integer.valueOf(0)));

		LOG.info("########### WHERE FirstName = ? AND LastName LIKE ? AND Age <> ? (order based parameters) ###########");
		utils.show(ownerSerivce.findByFirstNameLastNameAndAgeNativeSQL("Jola", "J", Integer.valueOf(0)));
	}

}
