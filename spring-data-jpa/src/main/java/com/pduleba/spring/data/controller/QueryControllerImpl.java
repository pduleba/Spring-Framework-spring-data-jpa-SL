package com.pduleba.spring.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pduleba.jpa.model.OwnerModel;
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
		final String USER_NAME = "Darek";
		
		LOG.info("----------- Execute Locking -----------");
		OwnerModel entity = ownerSerivce.findFirstByFirstName(USER_NAME);
		utils.show(entity);
		
		LOG.info("########### UPDATE - by UPDATE SQL ###########");
		utils.show(ownerSerivce.updateLastNameByFirstName("DD", USER_NAME));		
		
		LOG.info("########### UPDATE - by Repository ###########");
		entity.setLastName("DDD");
		ownerSerivce.create(entity);
		
		LOG.info("----------- Complete -----------");
	}
}
