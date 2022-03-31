package com.assignment.librarymanagementsystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.librarymanagementsystem.model.User;
import com.assignment.librarymanagementsystem.repository.BookRepository;
import com.assignment.librarymanagementsystem.repository.UserRepository;

@Service
public class CommonService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	/**
	 * checks that entered token (user) is present in database or not
	 * @param token
	 * @return true if token is present else false
	 */
	public boolean isTokenPresent(String token) {
		return userRepository.existsByToken(token);
	}
	
	public User getUserByToken(String token) {
		return userRepository.findByToken(token);
	}

}
