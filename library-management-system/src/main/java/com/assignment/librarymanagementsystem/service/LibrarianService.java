package com.assignment.librarymanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.librarymanagementsystem.model.User;
import com.assignment.librarymanagementsystem.response.IssuedBook;
import com.assignment.librarymanagementsystem.response.ResponseMessage;
import com.assignment.librarymanagementsystem.response.Student;
import com.assignment.librarymanagementsystem.model.Role;
import com.assignment.librarymanagementsystem.model.Book;

@Service
public class LibrarianService extends CommonService {
	
	public boolean isAllowed(Role role) {
		return role.getRole().equalsIgnoreCase("Librarian");
	}

	
	public ResponseEntity<?> getAllBooks(String token) {
		
		if (isTokenPresent(token)) {
			
			User user = getUserByToken(token);
			
			if (isAllowed(user.getRole())) {
				
				List<Book> books = bookRepository.findAll();
				return new ResponseEntity<>(books, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(new ResponseMessage("User not allowed"), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(new ResponseMessage("User not found"), HttpStatus.UNAUTHORIZED);
		
	}

	public ResponseEntity<?> getStudentsWithBooks(String token) {

		if (isTokenPresent(token)) {

			User user = getUserByToken(token);

			if (isAllowed(user.getRole())) {

				List<Student> students = getAllStudentsWithBooks();
				return new ResponseEntity<>(students, HttpStatus.OK);
			}

			return new ResponseEntity<>(new ResponseMessage("User not allowed"), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(new ResponseMessage("User not found"), HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<?> getBooksIssued(String token) {

		if (isTokenPresent(token)) {

			User user = getUserByToken(token);

			if (isAllowed(user.getRole())) {

				List<Book> books = bookRepository.findAll();
				List<IssuedBook> booksIssued = new ArrayList<>();
				
				books.stream().filter(b -> (b.getAvailableCopies() < b.getTotalCopies())).forEach(b -> {
					Set<String> users = b.getUsers().stream().map(u -> u.getUsername()).collect(Collectors.toSet());
					booksIssued.add(new IssuedBook(b.getName(), users));
				});				
				return new ResponseEntity<>(booksIssued, HttpStatus.OK);
			}

			return new ResponseEntity<>(new ResponseMessage("User not allowed"), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(new ResponseMessage("User not found"), HttpStatus.UNAUTHORIZED);
	}

	private List<Student> getAllStudentsWithBooks() {
		List<User> studentsWithBooks = userRepository.findUsersByRoleId(2);
		List<Student> students = new ArrayList<>();
		studentsWithBooks.stream().forEach(s -> {
			Set<String> books = s.getBooks().stream().map(b -> b.getName()).collect(Collectors.toSet());
			students.add(new Student(s.getUsername(), books));
		});
		return students;
	}
	
}
