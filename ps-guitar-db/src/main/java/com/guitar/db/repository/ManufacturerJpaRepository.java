package com.guitar.db.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guitar.db.model.Manufacturer;

@Repository
@Transactional
public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer, Long>{

}
