package com.example.SpringBootRestApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringBootRestApp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
