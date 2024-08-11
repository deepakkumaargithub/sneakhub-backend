package com.example.SpringBootRestApp.service;

import com.example.SpringBootRestApp.exception.UserException;
import com.example.SpringBootRestApp.model.User;

public interface UserService {

	public User findUserById(Long userId)throws UserException;
	
	public User findUserProfileByJwt(String jwt)throws UserException;
}
