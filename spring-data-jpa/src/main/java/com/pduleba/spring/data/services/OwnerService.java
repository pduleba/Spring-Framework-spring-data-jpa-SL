package com.pduleba.spring.data.services;

import java.util.List;

import com.pduleba.jpa.model.OwnerModel;

public interface OwnerService {

	void create(OwnerModel owner);

	void createAll(List<OwnerModel> owners);

	OwnerModel getById(long ownerId);

	void update(OwnerModel owner);

	void delete(OwnerModel owner);

	long count();

	List<OwnerModel> getByFirstName(String carName);

	List<OwnerModel> findByFirstNameLastNameAndAgeNameBased(String first, String lastLike, Integer ageNot);

	List<OwnerModel> findByFirstNameLastNameAndAgeOrderBased(String first, String lastLike, Integer ageNot);

	List<OwnerModel> findByFirstNameLastNameAndAgeNativeSQL(String first, String lastLike, Integer ageNot);
}
