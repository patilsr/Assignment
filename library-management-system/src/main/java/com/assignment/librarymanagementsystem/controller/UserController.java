package com.assignment.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.librarymanagementsystem.model.Role;
import com.assignment.librarymanagementsystem.model.User;
import com.assignment.librarymanagementsystem.repository.RoleRepository;
import com.assignment.librarymanagementsystem.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository repo;
	@Autowired
	RoleRepository roleRepo;

	@GetMapping("/users")
	@ResponseBody
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@PostMapping("/users/adduser")
	public String addUser(@RequestBody User user) {
		User u = new User(user.getId(), user.getUsername(), user.getRole());
		return "User Added " + repo.save(u).toString();
	}
	
	@GetMapping("/Role/")
	public List<Role> allRoles() {
		return roleRepo.findAll();
	}
	
	@PostMapping("/Role/addrole")
	public String addRole(@RequestBody Role role) {
		return "Role added " + roleRepo.save(role);
	}

}
