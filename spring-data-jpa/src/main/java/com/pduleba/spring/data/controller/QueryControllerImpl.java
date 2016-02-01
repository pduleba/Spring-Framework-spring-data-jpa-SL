package com.pduleba.spring.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
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
		LOG.info("----------- Usage of Paging and Sorting -----------");
		LOG.info("########### Paging ###########");
		utils.show(ownerSerivce.findByActiveTrue(2, 4));
		
		LOG.info("########### Paging & Sorting ###########");
		utils.show(ownerSerivce.findByActiveTrue(2, 4, Direction.DESC, OwnerModel.FIELD_FIRST_NAME));
		
		LOG.info("########### Sorting ###########");
		utils.show(ownerSerivce.findByActiveTrue(Direction.DESC, OwnerModel.FIELD_FIRST_NAME));
	}

}
