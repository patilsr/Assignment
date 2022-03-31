package com.assignment.librarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.librarymanagementsystem.response.ResponseMessage;
import com.assignment.librarymanagementsystem.service.LibrarianService;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

	@Autowired
	LibrarianService librarianService;

	@GetMapping("/allBooks")
	public ResponseEntity<?> getAllBooks(@RequestHeader(value = "Authorization") String bearer) {
		
		if (bearer.startsWith("Bearer ")) {
			String token = bearer.substring(7);
			return librarianService.getAllBooks(token);
		}
		
		return new ResponseEntity<>(new ResponseMessage("Authorization token is not properly passed"), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

	@GetMapping("/studentsList")
	public ResponseEntity<?> getStudentsWithBooks(@RequestHeader(value = "Authorization") String bearer) {
		
		if (bearer.startsWith("Bearer ")) {
			String token = bearer.substring(7);
			return librarianService.getStudentsWithBooks(token);
		}
		
		return new ResponseEntity<>(new ResponseMessage("Authorization token is not properly passed"), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

	@GetMapping("/booksIssued")
	public ResponseEntity<?> getBooksIssued(@RequestHeader(value = "Authorization") String bearer) {
		
		if (bearer.startsWith("Bearer ")) {
			String token = bearer.substring(7);
			return librarianService.getBooksIssued(token);
		}
		
		return new ResponseEntity<>(new ResponseMessage("Authorization token is not properly passed"), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

}
