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

	List<OwnerModel> getByFirstName(String firstName);

	@Query(value = "SELECT o FROM OwnerModel o WHERE o.firstName = :firstName AND o.lastName LIKE :lastName% AND o.age <> :age")
	List<OwnerModel> findByFirstNameLastNameAndAgeNameBased(
			@Param(value = "firstName") String first,
			@Param(value = "lastName") String lastLike, 
			@Param(value = "age") Integer ageNot);


	@Query(value = "SELECT o FROM OwnerModel o WHERE o.firstName = ?1 AND o.lastName LIKE ?2% AND o.age <> ?3")
	List<OwnerModel> findByFirstNameLastNameAndAgeOrderBased(String first, String lastLike, Integer ageNot);
	
	@Query(value = "SELECT o.* FROM T_OWNER o WHERE o.FIRST_NAME = ?1 AND o.LAST_NAME LIKE ?2% AND o.AGE <> ?3", nativeQuery = true)
	List<OwnerModel> findByFirstNameLastNameAndAgeNativeSQL(String first, String lastLike, Integer ageNot);

}
