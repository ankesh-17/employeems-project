package com.apps.employeems.controllers;

import com.apps.employeems.dto.CreateEmployeeRequest;
import com.apps.employeems.dto.EmployeeDetails;
import com.apps.employeems.dto.UpdateEmployeeRequest;
import com.apps.employeems.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/employees")
@RestController
public class EmployeeRestController {
	
	@Autowired
	private IEmployeeService service;

	/*
	 * method for post request on /employees/add and adding employee
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public EmployeeDetails addEmployee(@RequestBody CreateEmployeeRequest request) {
		return service.add(request);
	}

	/*
	 * method for getting all employees on /employees/
	 */
	@GetMapping
	public List<EmployeeDetails> getEmployee() {
		return service.findAllEmployees();
	}

	/*
	 * method for getting employee by id
	 */
	@GetMapping("/byid/{id}")
	public EmployeeDetails findEmployeeById(@PathVariable("id") int empId) {
		return service.findEmployeeDetailsById(empId);
	}

	/*
	 * method for getting the employee by their first name on /employees/byfirstname/firstname
	 */
	@GetMapping("/byfirstname/{firstName}")
	public List<EmployeeDetails> findEmployeeByFirstName(@PathVariable("firstName") String firstName) {
		return service.findEmployeesByFirstName(firstName);
	}

	/*
	 * method for updating the employee on /employees/update
	 */
	@PutMapping("/update")
	public EmployeeDetails updateEmployeeDetails(@RequestBody UpdateEmployeeRequest request) {
		return service.updateEmployee(request);
	}

	/*
     * method for deleting the employee on /employees/delete/id
     */
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") int empId) {
		return service.deleteById(empId);
	}
}
