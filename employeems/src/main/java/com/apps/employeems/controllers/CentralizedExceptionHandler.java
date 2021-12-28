package com.apps.employeems.controllers;

import com.apps.employeems.exceptions.EmployeeNotFoundException;
import com.apps.employeems.exceptions.InvalidEmployeeIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmployeeNotFoundException.class)
	public String handleEmployeeNotFound(EmployeeNotFoundException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidEmployeeIdException.class)
	public String handleInvalidId(InvalidEmployeeIdException e) {
		return e.getMessage();
	}
}
