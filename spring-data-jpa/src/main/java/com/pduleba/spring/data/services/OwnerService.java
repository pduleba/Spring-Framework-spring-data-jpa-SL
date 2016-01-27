package com.pduleba.spring.data.services;

import java.util.List;

import com.pduleba.jpa.model.OwnerModel;

public interface OwnerService {

	void create(OwnerModel owner);

	void createAll(List<OwnerModel> owners);

	OwnerModel read(long ownerId);

	void update(OwnerModel owner);

	void delete(OwnerModel owner);

	int getNumberOfOwners();

	List<OwnerModel> queryForList(String carName);

}
