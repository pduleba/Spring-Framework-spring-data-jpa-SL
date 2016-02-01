package com.pduleba.spring.data.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pduleba.jpa.model.OwnerModel;

public interface OwnerService {

	void create(OwnerModel owner);

	void createAll(List<OwnerModel> owners);

	OwnerModel getById(long ownerId);

	void update(OwnerModel owner);

	void delete(OwnerModel owner);

	long count();
	
	public Page<OwnerModel> findByActiveTrue(int page, int size);

	String getCustomMethodResult();
	
}
