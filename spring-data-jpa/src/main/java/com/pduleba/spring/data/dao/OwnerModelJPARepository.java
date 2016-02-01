package com.pduleba.spring.data.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.jpa.model.OwnerModel;

@Repository
@Transactional
public interface OwnerModelJPARepository extends JpaRepository<OwnerModel, Long>, CustomOwnerModelJPARepository {

	OwnerModel getById(Long ownerId);

	void delete(OwnerModel owner);

	long count();

	Page<OwnerModel> findByActiveTrue(Pageable page);

	Iterable<OwnerModel> findByActiveTrue(Sort sort);
	
}
