package com.pduleba.spring.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.jpa.model.OwnerModel;

@Repository
@Transactional
public interface OwnerDao extends JpaRepository<OwnerModel, Long> {

	OwnerModel getById(Long ownerId);

	void delete(OwnerModel owner);

	long count();

	List<OwnerModel> getByFirstName(String firstName);

	List<OwnerModel> getByFirstNameLikeOrLastNameLike(String firstName, String lastName);

	List<OwnerModel> getByFirstNameIsAndLastNameEqualsAndAgeNot(String firstName, String lastName, Integer age);

	List<OwnerModel> getByFirstNameNotLike(String firstName);

	List<OwnerModel> getByFirstNameStartingWithOrFirstNameEndingWithOrFirstNameContaining(String firstNameStartingWith,
			String firstNameEndingWith, String firstNameContaining);
	
	List<OwnerModel> getByAgeLessThanEqualAndAgeGreaterThan(int ageLessThanEqual, int ageGreaterThan);

	List<OwnerModel> getByAgeBeforeAndAgeAfterAndAgeBetween(int i, int j, int k, int l);
	
	List<OwnerModel> getByActiveTrue();
}
