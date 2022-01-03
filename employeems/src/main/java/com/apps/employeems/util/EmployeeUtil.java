package com.apps.employeems.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apps.employeems.dto.EmployeeDetails;
import com.apps.employeems.entities.Employee;

@Component
public class EmployeeUtil {

	/*
	 * method for converting a employee object to a dto object
	 */
	public EmployeeDetails toDetails(Employee employee) {
		EmployeeDetails details = new EmployeeDetails();
		details.setId(employee.getId());
		details.setFirstName(employee.getFirstName());
		details.setLastName(employee.getLastName());
		details.setEmail(employee.getEmail());
		details.setSalary(employee.getSalary());
		return details;
	}

	/*
	 * method for converting list of employee objects to dto objects
	 */
	public List<EmployeeDetails> toDetailsList(Collection<Employee> employees) {

		List<EmployeeDetails> desired = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeeDetails details = toDetails(employee);
			desired.add(details);
		}
		return desired;

	}

}
