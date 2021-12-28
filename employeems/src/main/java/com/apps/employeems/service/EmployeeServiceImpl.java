package com.apps.employeems.service;

import com.apps.employeems.dao.IEmployeeRepository;
import com.apps.employeems.dto.CreateEmployeeRequest;
import com.apps.employeems.dto.EmployeeDetails;
import com.apps.employeems.dto.UpdateEmployeeRequest;
import com.apps.employeems.entities.Employee;
import com.apps.employeems.exceptions.EmployeeNotFoundException;
import com.apps.employeems.util.EmployeeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository dao;

	@Autowired
	private EmployeeUtil util;

	@Override
	public EmployeeDetails add(CreateEmployeeRequest request) {

		Employee employee = new Employee();
		employee.setFirstName(request.getFirstName());
		employee.setLastName(request.getLastName());
		employee.setEmail(request.getEmail());
		Employee saved = dao.save(employee);

		return util.toDetails(saved);
	}

	@Override
	public List<EmployeeDetails> findAllEmployees() {
		List<Employee> employeeList = dao.findAll();
		return util.toDetailsList(employeeList);
	}

	@Override
	public Employee findById(Integer empId) {
		Optional<Employee> optional = dao.findById(empId);

		if (!optional.isPresent()) {
			throw new EmployeeNotFoundException("employee not found for id=" + empId);
		}
		return optional.get();
	}

	@Override
	public EmployeeDetails findEmployeeDetailsById(Integer empId) {
		Employee employee = findById(empId);
		return util.toDetails(employee);
	}

	@Override
	public List<EmployeeDetails> findEmployeesByFirstName(String firstName) {
		List<Employee> list = dao.findByFirstName(firstName);
		return util.toDetailsList(list);
	}

	@Override
	public EmployeeDetails updateEmployee(UpdateEmployeeRequest request) {
		Employee employee = findById(request.getId());

		employee.setFirstName(request.getFirstName());
		employee.setLastName(request.getLastName());
		employee.setEmail(request.getEmail());

		Employee updatedEmployee = dao.save(employee);
		return util.toDetails(updatedEmployee);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}
}
