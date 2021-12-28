package com.apps.employeems.service;

import com.apps.employeems.dto.CreateEmployeeRequest;
import com.apps.employeems.dto.EmployeeDetails;
import com.apps.employeems.dto.UpdateEmployeeRequest;
import com.apps.employeems.entities.Employee;

import java.util.List;

public interface IEmployeeService {

	EmployeeDetails add(CreateEmployeeRequest request);

	Employee findById(Integer empId);
	
	EmployeeDetails findEmployeeDetailsById(Integer empId);

	List<EmployeeDetails> findAllEmployees();

	List<EmployeeDetails> findEmployeesByFirstName(String firstName);
	
	EmployeeDetails updateEmployee(UpdateEmployeeRequest request);

	void deleteById(int id);
}
