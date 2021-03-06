package com.kodlamaio.hrmsDemo5.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmsDemo5.entities.concretes.School;

@Repository
public interface SchoolDao extends JpaRepository<School, Integer> {
	List<School> findAllByOrderByEndDateDesc();
	List<School> findByEndDateIsNull();
	List<School> findByEndDateIsNotNullOrderByEndDateDesc();
}
