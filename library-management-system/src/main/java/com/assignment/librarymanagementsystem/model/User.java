package com.assignment.librarymanagementsystem.model;

import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Users")
public class User {
	
	@Id
	@JsonIgnore
	private long id;
	@Column(unique = true)
	private String username;
	@ManyToOne
	@JsonIgnore
	private Role role;
	@JsonIgnore
	private String token;
	@ManyToMany
	Set<Book> books = new HashSet<>();
	
	public User() {
		this.token = UUID.randomUUID().toString();
	}

	public User(long id, String username, Role role) {
		this.id = id;
		this.username = username;
		this.role = role;
		this.token = UUID.randomUUID().toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getToken() {
		return token;
	}
	
	public void addBook(Book book) {
		this.books.add(book);
		book.getUsers().add(this);
	}
	
	public void removeBook(Book book) {
		this.books.remove(book);
		book.getUsers().remove(this);
	}
	
	public Set<Book> getBooks() {
		return books;
	}
	
	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", role=" + role + ", token=" + token + "]";
	}
	
}
