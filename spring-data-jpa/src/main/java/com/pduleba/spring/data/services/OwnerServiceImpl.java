package com.pduleba.spring.data.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pduleba.jpa.model.OwnerModel;
import com.pduleba.spring.data.dao.OwnerModelJPARepository;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerModelJPARepository ownerDao;

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

	@Override
	public Page<OwnerModel> findByActiveTrue(int page, int size) {
		return findByActiveTrue(page, size, null);
	}

	public Page<OwnerModel> findByActiveTrue(int page, int size, Sort sort) {
		Pageable pageable = new PageRequest(page, size, sort);
		
		return ownerDao.findByActiveTrue(pageable);
	}
	
	@Override
	public String getCustomMethodResult() {
		return ownerDao.getCustomMethodResult();
	}
}
