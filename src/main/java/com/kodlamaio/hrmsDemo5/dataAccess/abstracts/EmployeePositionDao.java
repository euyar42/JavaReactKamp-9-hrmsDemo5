package com.kodlamaio.hrmsDemo5.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmsDemo5.entities.concretes.EmployeePosition;

@Repository
public interface EmployeePositionDao extends JpaRepository<EmployeePosition, Integer> {
	boolean existsEmployeePositionByPositionNameIgnoreCase(String positionName);
}
