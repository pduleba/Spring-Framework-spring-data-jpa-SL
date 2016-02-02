package com.pduleba.spring.data.controller;

import java.util.List;

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
		LOG.info("----------- Execute Auditing -----------");
		LOG.info("########### SELECT ###########");
		List<OwnerModel> list = ownerSerivce.getByFirstName("Darek");
		utils.show(list);
		
		LOG.info("########### UPDATE - DOES NOT WORK - update query ###########");
		utils.show(ownerSerivce.updateLastNameByFirstName("DD", "Darek"));		
		
		LOG.info("########### UPDATE - WORKS ###########");
		OwnerModel entity = ownerSerivce.getById(list.get(0).getId());
		entity.setLastName("DDD");
		ownerSerivce.create(entity);
		
		LOG.info("----------- Complete -----------");
	}
}
