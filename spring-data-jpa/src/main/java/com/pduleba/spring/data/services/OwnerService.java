package com.pduleba.spring.data.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import com.pduleba.jpa.model.OwnerModel;

public interface OwnerService {

	void create(OwnerModel owner);

	void createAll(List<OwnerModel> owners);

	OwnerModel getById(long ownerId);

	void update(OwnerModel owner);

	void delete(OwnerModel owner);

	long count();
	
	public Page<OwnerModel> findByActiveTrue(int page, int size);
	
	public Page<OwnerModel> findByActiveTrue(int page, int size, Direction direction, String sortFieldName);

	public Iterable<OwnerModel> findByActiveTrue(Direction desc, String fieldFirstName);
	
}
