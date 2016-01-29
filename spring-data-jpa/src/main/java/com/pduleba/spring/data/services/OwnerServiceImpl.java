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
	public List<OwnerModel> getByFirstNameNotLike(String firstName) {
		return ownerDao.getByFirstNameNotLike(firstName);
	}
}
