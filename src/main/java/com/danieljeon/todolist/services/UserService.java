package com.danieljeon.todolist.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljeon.todolist.models.User;
import com.danieljeon.todolist.repositories.UserRepo;

@Service
public class UserService {
	@Autowired
	private final UserRepo userRepo;
	
	public UserService(UserRepo ur) {
		this.userRepo = ur;
	}
	
	// register user and hash their pw
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);
	}
	
	// find user by email
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	// find user by id
	public User findUserById(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		else {
			return null;
		}
	}
	
	// authenticate user
	public boolean authenticateUser(String email, String password) {
		// first find user by email
		User user = userRepo.findByEmail(email);
		
		if (user == null) {
			return false;
		}
		else {
			// if the passwords match, return true, else, return false
			return (BCrypt.checkpw(password, user.getPassword()));
		}
	}
	
	public List<User> all() {
		return userRepo.findAll();
	}
}
