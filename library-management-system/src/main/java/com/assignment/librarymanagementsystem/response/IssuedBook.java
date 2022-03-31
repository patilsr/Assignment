package com.assignment.librarymanagementsystem.response;

import java.util.Set;

public class IssuedBook {

	private String name;
	private Set<String> users;
	
	public IssuedBook(String name, Set<String> users) {
		this.name = name;
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Issued Book [name=" + name + ", users=" + users + "]";
	}
}
