package com.assignment.librarymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.librarymanagementsystem.model.Book;
import com.assignment.librarymanagementsystem.model.User;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	public List<Book> findByUsers(User user);
	
//	public List<Book> findByUserId(long user_id);
	
	public Book findByName(String name);
	
	public boolean existsByName(String name);
}
