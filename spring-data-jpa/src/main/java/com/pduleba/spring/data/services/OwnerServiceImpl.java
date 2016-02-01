package com.pduleba.spring.data.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

	@Override
	public Page<OwnerModel> findByActiveTrue(int page, int size) {
		return findByActiveTrue(page, size, null);
	}
	
	@Override
	public Page<OwnerModel> findByActiveTrue(int page, int size, Direction direction, String sortFieldName) {
		Sort sort = new Sort(direction, sortFieldName);
		
		return findByActiveTrue(page, size, sort);
	}

	public Page<OwnerModel> findByActiveTrue(int page, int size, Sort sort) {
		Pageable pageable = new PageRequest(page, size, sort);
		
		return ownerDao.findByActiveTrue(pageable);
	}
	
	@Override
	public Iterable<OwnerModel> findByActiveTrue(Direction direction, String sortFieldName) {
		Sort sort = new Sort(direction, sortFieldName);
		
		return ownerDao.findByActiveTrue(sort);
	}
}
