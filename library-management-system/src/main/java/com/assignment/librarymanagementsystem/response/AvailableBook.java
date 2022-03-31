package com.assignment.librarymanagementsystem.response;

public class AvailableBook {
	
	private String name;
	private int availableCopies;

	public AvailableBook(String name, int availableCopies) {
		this.name = name;
		this.availableCopies = availableCopies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
