package com.pduleba.spring.data.services;

import java.util.Collection;
import java.util.List;

import com.pduleba.jpa.model.CarModel;
import com.pduleba.jpa.model.OwnerModel;

public interface UtilityService {
	
	public enum Mode {
		CREATE,
		READ,
		UPDATE,
		DELETE,
		UPDATE_OR_COUNT
	}
	
	public List<OwnerModel> getData();
	
	void show(Object entity, Mode mode);

	void show(Object entity);
	
	void show(Collection<?> entities, Mode mode);

	void show(Collection<?> entities);

	void show(int updateOrCount);

	CarModel getCar();
}
