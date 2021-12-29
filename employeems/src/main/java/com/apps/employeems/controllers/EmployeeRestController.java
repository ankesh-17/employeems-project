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
	//private static final Logger Log = LoggerFactory.getLogger(EmployeeRestController.class);

	@Autowired
	private IEmployeeService service;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public EmployeeDetails addEmployee(@RequestBody CreateEmployeeRequest request) {
		return service.add(request);
	}

	@GetMapping
	public List<EmployeeDetails> getEmployee() {
		return service.findAllEmployees();
	}

	@GetMapping("/byid/{id}")
	public EmployeeDetails findEmployeeById(@PathVariable("id") int empId) {
		return service.findEmployeeDetailsById(empId);
	}

	@GetMapping("/byfirstname/{firstName}")
	public List<EmployeeDetails> findEmployeeByFirstName(@PathVariable("firstName") String firstName) {
		return service.findEmployeesByFirstName(firstName);
	}

	@PutMapping("/update")
	public EmployeeDetails updateEmployeeDetails(@RequestBody UpdateEmployeeRequest request) {
		return service.updateEmployee(request);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") int empId) {
		service.deleteById(empId);
		return "employee with id " + empId + " deleted";
	}
}
