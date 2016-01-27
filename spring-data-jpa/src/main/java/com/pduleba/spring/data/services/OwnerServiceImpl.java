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
		ownerDao.create(owner);
	}
	
	@Override
	public void createAll(List<OwnerModel> owners) {
		ownerDao.createAll(owners);
	}

	@Override
	public OwnerModel read(long ownerId) {
		return ownerDao.read(ownerId);
	}

	@Override
	public void update(OwnerModel owner) {
		ownerDao.update(owner);
	}

	@Override
	public void delete(OwnerModel owner) {
		ownerDao.delete(owner);
	}

	@Override
	public int getNumberOfOwners() {
		return ownerDao.getNumberOfOwners();
	}

	// ------------------------------------------------
	//					Query methods
	// ------------------------------------------------

	@Override
	public List<OwnerModel> queryForList(String firstName) {
		return ownerDao.queryForList(firstName);
	}
}
