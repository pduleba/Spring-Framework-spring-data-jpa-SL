package com.guitar.db.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guitar.db.model.ModelType;

@Repository
@Transactional
public interface ModelTypeJpaRepository extends JpaRepository<ModelType, Long> {

}
