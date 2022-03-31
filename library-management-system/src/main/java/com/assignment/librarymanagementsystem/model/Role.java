package com.assignment.librarymanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int id;
	private String role;
	
	public Role() {}
	
	public Role(int id, String role) {
		this.id = id;
		this.role = role;
	}

	public Role(String role) {
		this.role = role;
	}
	
	public Role(Role role) {
		this.role = role.getRole();
	}

	public int getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return role;
	}
	
}