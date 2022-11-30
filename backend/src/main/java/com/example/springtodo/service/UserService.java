package com.example.springtodo.service;

import com.example.springtodo.entity.User;
import com.example.springtodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User createUser(String email, String password, String username) {
		try {
			User newUser = username == null ?
					new User(email, password) : new User(email, username, password);
			return userRepository.save(newUser);
		} catch (DataIntegrityViolationException exception) {
			throw new IllegalArgumentException("이미 존재하는 아이디입니다");
		}
	}

	public User getUser(Integer id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty()) {
			throw new IllegalArgumentException("해당 User가 존재하지 않습니다");
		}
		return userOptional.get();
	}

	public User getUser(String email, String password) {
		Optional<User> userOptional = this.userRepository.findByEmailAndPassword(email, password);
		if (userOptional.isEmpty()) {
			throw new IllegalArgumentException("해당 User가 존재하지 않습니다");
		}
		return userOptional.get();
	}
}
