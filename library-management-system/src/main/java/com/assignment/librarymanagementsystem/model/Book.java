package com.assignment.librarymanagementsystem.model;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Books")
public class Book {
	
	@Id
	@JsonIgnore
	private long id;
	@Column(unique = true)
	private String name;
	private int totalCopies;
	private int availableCopies;
	
	@ManyToMany(mappedBy = "books")
	@JsonIgnore
	Set<User> users = new HashSet<>();
	
	public Book() {}

	public Book(long book_id, String name, int totalCopies) {
		this.id = book_id;
		this.name = name;
		this.totalCopies = totalCopies;
		this.availableCopies = totalCopies;
	}
	
	public Book(long id, String name, int totalCopies, int availableCopies) {
		this.id = id;
		this.name = name;
		this.totalCopies = totalCopies;
		this.availableCopies = availableCopies;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	public Set<User> getUsers() {
		return users;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", totalCopies=" + totalCopies + ", availableCopies="
				+ availableCopies + "]";
	}
	
}
