package com.apps.employeems.exceptions;

public class InvalidEmployeeIdException extends RuntimeException{

    public InvalidEmployeeIdException(String msg){
        super(msg);
    }
}
