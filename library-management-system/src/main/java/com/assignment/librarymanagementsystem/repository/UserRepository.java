package com.assignment.librarymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.librarymanagementsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public boolean existsByToken(String token);
	
	public User findByToken(String token);
	
	public List<User> findUsersByRoleId(int roleId);

}
