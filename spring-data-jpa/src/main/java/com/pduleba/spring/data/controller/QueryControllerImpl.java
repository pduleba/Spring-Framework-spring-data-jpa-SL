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
		LOG.info("----------- Usage of Custom Repository -----------");
		LOG.info("########### JPA DSL method call ###########");
		utils.show(ownerSerivce.findByActiveTrue(2, 4));
		
		LOG.info("########### custom method call ###########");
		utils.show(ownerSerivce.getCustomMethodResult());
	}

}
