package com.pduleba.spring.data.dao;

// Custom Repository
// WARNING : name of class must be in form : 
// OwnerModelJPARepository (interface which is extended by JpaRepository<M, I>) + Impl (postfix)
public class OwnerModelJPARepositoryImpl implements CustomOwnerModelJPARepository {

	@Override
	public String getCustomMethodResult() {
		return "Custom method result";
	}
}
