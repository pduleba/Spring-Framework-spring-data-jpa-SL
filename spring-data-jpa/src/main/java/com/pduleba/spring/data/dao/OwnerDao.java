package com.pduleba.spring.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.jpa.model.OwnerModel;

@Repository
@Transactional
public interface OwnerDao extends JpaRepository<OwnerModel, Long> {

	OwnerModel getById(Long ownerId);

	void delete(OwnerModel owner);

	long count();

	@Query // Named Query
	List<OwnerModel> findByFirstNameAndAgeNotZero(@Param(value = "firstName") String firstName);
	
}
