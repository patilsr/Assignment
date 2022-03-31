package com.assignment.librarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.librarymanagementsystem.response.ResponseMessage;
import com.assignment.librarymanagementsystem.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("/myBooks")
	public ResponseEntity<?> getMyBooks(@RequestHeader(value = "Authorization") String bearer) {

		if (bearer.startsWith("Bearer ")) {
			String token = bearer.substring(7);
			return studentService.getMyBooks(token);
		}

		return new ResponseEntity<>(new ResponseMessage("Authorization token is not properly paased"), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

	@GetMapping("/availableBooks")
	public ResponseEntity<?> getBooks(@RequestHeader(value = "Authorization") String bearer) {
		
		System.out.println(Thread.currentThread().getName());
		if (bearer.startsWith("Bearer ")) {
			String token = bearer.substring(7);
			try {
				return studentService.getBooks(token).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new ResponseEntity<>(new ResponseMessage("Authorization token is not properly paased"), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

	@GetMapping("/borrowBook/{name}")
	public ResponseEntity<?> borrowBook(@RequestHeader(value = "Authorization") String bearer,
			@PathVariable(value = "name") String name) {

		if (bearer.startsWith("Bearer ")) {
			String token = bearer.substring(7);
			return studentService.borrowBook(token, name);
		}

		return new ResponseEntity<>(new ResponseMessage("Authorization token is not properly passed"), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

	@GetMapping("/returnBook/{name}")
	public ResponseEntity<?> returnBook(@RequestHeader(value = "Authorization") String bearer,
			@PathVariable(value = "name") String name) {

		if (bearer.startsWith("Bearer ")) {
			String token = bearer.substring(7);
			return studentService.returnBook(token, name);
		}

		return new ResponseEntity<>(new ResponseMessage("Authorization token is not properly passed"), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

}
