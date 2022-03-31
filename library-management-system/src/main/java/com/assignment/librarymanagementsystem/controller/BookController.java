package com.assignment.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.librarymanagementsystem.model.Book;
import com.assignment.librarymanagementsystem.repository.BookRepository;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BookRepository bookRepo;
	
	@GetMapping
	@ResponseBody
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}
	
	@PostMapping("/addbook")
	public String addBook(@RequestBody Book book) {
		Book b = new Book(book.getId(), book.getName(), book.getTotalCopies());
		return "Book Added " + bookRepo.save(b);
	}

}
