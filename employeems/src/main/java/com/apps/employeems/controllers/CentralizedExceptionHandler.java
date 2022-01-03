package com.apps.employeems.controllers;

import com.apps.employeems.exceptions.EmployeeNotFoundException;
import com.apps.employeems.exceptions.InvalidEmployeeIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {

	/*
	 * method for handling exception of employee not found
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmployeeNotFoundException.class)
	public String handleEmployeeNotFound(EmployeeNotFoundException e) {
		return e.getMessage();
	}

	/*
	 * method for handling exception of wrong employee id 
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidEmployeeIdException.class)
	public String handleInvalidId(InvalidEmployeeIdException e) {
		return e.getMessage();
	}
}
