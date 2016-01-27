package com.pduleba.spring.data.dao;

import java.util.List;

import com.pduleba.jpa.model.OwnerModel;

public interface OwnerDao {

	void create(OwnerModel owner);

	void createAll(List<OwnerModel> owners);

	OwnerModel read(long ownerId);

	void update(OwnerModel owner);

	void delete(OwnerModel owner);

	int getNumberOfOwners();

	List<OwnerModel> queryForList(String firstName);
}
