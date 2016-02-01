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

	List<OwnerModel> getByFirstNameLikeOrLastNameLike(String firstName, String lastName);
	
	List<OwnerModel> getByFirstNameIsAndLastNameEqualsAndAgeNot(String firstName, String lastName, Integer age);
	
	List<OwnerModel> getByFirstNameNotLike(String firstName);

	List<OwnerModel> getByFirstNameStartingWithOrFirstNameEndingWithOrFirstNameContaining(String firstNameStartingWith,
			String firstNameEndingWith, String firstNameContaining);

	List<OwnerModel> getByAgeLessThanEqualAndAgeGreaterThan(int ageLessThanEqual, int ageGreaterThan);

	List<OwnerModel> getByAgeBeforeAndAgeAfterAndAgeBetween(int i, int j, int k, int l);

	List<OwnerModel> getByActiveTrue();

	List<OwnerModel> getByAgeIsNotNullOrActiveIsNull();

	List<OwnerModel> getByAgeInAndActiveNotIn(List<Integer> asList, List<Boolean> asList2);

	List<OwnerModel> getByCarsNameIn(List<String> in);

	List<OwnerModel> getByFirstNameIgnoreCaseAndLastName(String firstNameIgnoreCase, String lastName);

	List<OwnerModel> getByActiveOrderByFirstNameDesc(Boolean active);

	List<OwnerModel> findFirstByActiveTrueOrderByFirstNameAsc();

	List<OwnerModel> findTop2ByActiveTrueOrderByFirstNameAsc();

	List<OwnerModel> findDistinctByActiveTrueOrderByFirstNameAsc();

}
