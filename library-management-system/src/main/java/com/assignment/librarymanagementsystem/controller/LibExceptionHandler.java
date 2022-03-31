package com.assignment.librarymanagementsystem.controller;

import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
public class LibExceptionHandler {
	
	@ResponseBody
//	@ExceptionHandler(RoleMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String RoleMismatch(/*RoleMismatchException ex*/) {
//		return ex.getMessage();
		return "Exception Message";
	}

}
