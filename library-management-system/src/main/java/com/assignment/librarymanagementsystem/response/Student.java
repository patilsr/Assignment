package com.assignment.librarymanagementsystem.response;

import java.util.Set;

public class Student {
	
	private String username;
	private Set<String> books;
	
	public Student(String username, Set<String> books) {
		this.username = username;
		this.books = books;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getBooks() {
		return books;
	}

	public void setBooks(Set<String> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Student [username=" + username + ", books=" + books + "]";
	}
}
