package com.assignment.librarymanagementsystem.response;

public class ResponseBookName {
	
	private String name;

	public ResponseBookName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}	
}
