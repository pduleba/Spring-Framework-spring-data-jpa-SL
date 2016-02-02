package com.pduleba.spring.data.dao;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pduleba.jpa.model.OwnerModel;

@Repository
public interface OwnerDao extends JpaRepository<OwnerModel, Long> {

	OwnerModel getById(Long ownerId);

	void delete(OwnerModel owner);

	long count();

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	OwnerModel findFirstByFirstName(String firstName);
	
	@Modifying
	@Query(value = "UPDATE OwnerModel o SET o.lastName = :lastName WHERE o.firstName = :firstName")
	int updateLastNameByFirstName(@Param(value = "lastName") String lastName, @Param(value = "firstName") String firstName);

	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	<S extends OwnerModel> S saveAndFlush(S entity);
}
