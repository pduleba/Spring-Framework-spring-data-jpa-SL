package com.pduleba.spring.data.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pduleba.jpa.model.OwnerModel;
import com.pduleba.spring.data.dao.OwnerDao;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	@Override
	public void create(OwnerModel owner) {
		ownerDao.saveAndFlush(owner);
	}
	
	@Override
	public void createAll(List<OwnerModel> owners) {
		ownerDao.save(owners);
	}

	@Override
	public OwnerModel getById(long ownerId) {
		return ownerDao.getById(ownerId);
	}

	@Override
	public void update(OwnerModel owner) {
		ownerDao.saveAndFlush(owner);
	}

	@Override
	public void delete(OwnerModel owner) {
		ownerDao.delete(owner);
	}

	@Override
	public long count() {
		return ownerDao.count();
	}

	// ------------------------------------------------
	//					Query methods
	// ------------------------------------------------

	@Override
	public List<OwnerModel> getByFirstName(String firstName) {
		return ownerDao.getByFirstName(firstName);
	}
	
	@Override
	public List<OwnerModel> getByFirstNameLikeOrLastNameLike(String firstName, String lastName) {
		return ownerDao.getByFirstNameLikeOrLastNameLike(firstName, lastName);
	}
	
	@Override
	public List<OwnerModel> getByFirstNameIsAndLastNameEqualsAndAgeNot(String firstName, String lastName, Integer age) {
		return ownerDao.getByFirstNameIsAndLastNameEqualsAndAgeNot(firstName, lastName, age);
	}
	
	@Override
	public List<OwnerModel> getByFirstNameNotLike(String firstName) {
		return ownerDao.getByFirstNameNotLike(firstName);
	}
	
	@Override
	public List<OwnerModel> getByFirstNameStartingWithOrFirstNameEndingWithOrFirstNameContaining(
			String firstNameStartingWith, String firstNameEndingWith, String firstNameContaining) {
		return ownerDao.getByFirstNameStartingWithOrFirstNameEndingWithOrFirstNameContaining(firstNameStartingWith,
				firstNameEndingWith, firstNameContaining);
	}
	
	@Override
	public List<OwnerModel> getByAgeLessThanEqualAndAgeGreaterThan(int ageLessThanEqual, int ageGreaterThan) {
		return ownerDao.getByAgeLessThanEqualAndAgeGreaterThan(ageLessThanEqual, ageGreaterThan);
	}
	
	@Override
	public List<OwnerModel> getByAgeBeforeAndAgeAfterAndAgeBetween(int i, int j, int k, int l) {
		return ownerDao.getByAgeBeforeAndAgeAfterAndAgeBetween(i, j, k, l);
	}
	
	@Override
	public List<OwnerModel> getByActiveTrue() {
		return ownerDao.getByActiveTrue();
	}
	
	@Override
	public List<OwnerModel> getByAgeIsNotNullOrActiveIsNull() {
		return ownerDao.getByAgeIsNotNullOrActiveIsNull();
	}
	
	@Override
	public List<OwnerModel> getByAgeInAndActiveNotIn(List<Integer> in, List<Boolean> notIn) {
		return ownerDao.getByAgeInAndActiveNotIn(in, notIn);
	}
	
	@Override
	public List<OwnerModel> getByCarsNameIn(List<String> in) {
		return ownerDao.getByCarsNameIn(in);
	}
}
