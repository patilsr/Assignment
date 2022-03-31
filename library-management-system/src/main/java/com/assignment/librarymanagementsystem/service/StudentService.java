package com.assignment.librarymanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.assignment.librarymanagementsystem.model.User;
import com.assignment.librarymanagementsystem.response.AvailableBook;
//import com.assignment.librarymanagementsystem.response.ResponseBookName;
import com.assignment.librarymanagementsystem.response.ResponseMessage;
import com.assignment.librarymanagementsystem.model.Book;
import com.assignment.librarymanagementsystem.model.Role;

@Service
public class StudentService extends CommonService {

	public boolean isAllowed(Role role) {
		return role.getRole().equalsIgnoreCase("Student");
	}
	
	
	public ResponseEntity<?> getMyBooks(String token) {

		if (isTokenPresent(token)) {

			User user = getUserByToken(token);

			if (isAllowed(user.getRole())) {

				Set<Book> books = user.getBooks();
//				List<ResponseBookName> myBooks = new ArrayList<>();
//				books.stream().forEach(b -> {
//					myBooks.add(new ResponseBookName(b.getName()));
//				});
				
				List<String> myBooks = books.stream().map(b -> b.getName()).toList();
				return new ResponseEntity<>(myBooks, HttpStatus.OK);
			}

			return new ResponseEntity<>(new ResponseMessage("User not allowed"), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(new ResponseMessage("User not found"), HttpStatus.UNAUTHORIZED);
	}

	
	@Async("processExecutor")
	public CompletableFuture<ResponseEntity<?>> getBooks(String token) {
		

		if (isTokenPresent(token)) {

			User user = getUserByToken(token);

			if (isAllowed(user.getRole())) {

				List<Book> allBooks = bookRepository.findAll();
				List<AvailableBook> availableBooks = new ArrayList<>();
				allBooks.stream().forEach(b -> {
					availableBooks.add(new AvailableBook(b.getName(), b.getAvailableCopies()));
				});;
				return CompletableFuture.completedFuture(new ResponseEntity<>(availableBooks, HttpStatus.OK));
			}

			return CompletableFuture.completedFuture(new ResponseEntity<>(new ResponseMessage("User not allowed"), HttpStatus.UNAUTHORIZED));
		}

		return CompletableFuture.completedFuture(new ResponseEntity<>(new ResponseMessage("User not found"), HttpStatus.UNAUTHORIZED));
	}

	
	public ResponseEntity<?> borrowBook(String token, String name) {

		if (isTokenPresent(token)) {

			User user = getUserByToken(token);

			if (isAllowed(user.getRole())) {

				if (bookRepository.existsByName(name)) {

					Book book = bookRepository.findByName(name);

					if (user.getBooks().contains(book)) {
						return new ResponseEntity<>(new ResponseMessage("Book already issued to you"), HttpStatus.OK);
					}

					if (book.getAvailableCopies() < 1) {
						return new ResponseEntity<>(new ResponseMessage("Book not available, book = " + name), HttpStatus.BAD_REQUEST);
					}

					user.addBook(book);
					book.setAvailableCopies(book.getAvailableCopies() - 1);
					userRepository.save(user);
					bookRepository.save(book);

					return new ResponseEntity<>(new ResponseMessage("Book issued. book = " + book.getName()), HttpStatus.OK);
				}

				return new ResponseEntity<>(new ResponseMessage("Book doesn't exist, for name = " + name), HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<>(new ResponseMessage("User not allowed"), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(new ResponseMessage("User not found"), HttpStatus.UNAUTHORIZED);
	}

	
	public ResponseEntity<?> returnBook(String token, String name) {

		if (isTokenPresent(token)) {

			User user = getUserByToken(token);

			if (isAllowed(user.getRole())) {

				if (bookRepository.existsByName(name)) {

					Book book = bookRepository.findByName(name);

					if (user.getBooks().contains(book)) {

						user.removeBook(book);
						book.setAvailableCopies(book.getAvailableCopies() + 1);
						userRepository.save(user);
						bookRepository.save(book);

						return new ResponseEntity<>(new ResponseMessage("Book returned. book : " + book.getName()), HttpStatus.OK);
					}

					return new ResponseEntity<>(new ResponseMessage("You don't have this book to return"), HttpStatus.BAD_REQUEST);
				}

				return new ResponseEntity<>(new ResponseMessage("Book doesn't exist, book = " + name), HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<>(new ResponseMessage("User not allowed"), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(new ResponseMessage("User not found"), HttpStatus.UNAUTHORIZED);
	}

}
