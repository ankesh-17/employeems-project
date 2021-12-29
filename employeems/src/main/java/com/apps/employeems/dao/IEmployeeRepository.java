package com.apps.employeems.dao;

import com.apps.employeems.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByFirstName(String FirstName);
}
