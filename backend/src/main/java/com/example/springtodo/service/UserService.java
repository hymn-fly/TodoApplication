package com.example.springtodo.service;

import com.example.springtodo.entity.User;
import com.example.springtodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public User createUser(String email, String password, String username) {
		try {
			String encodedPassword = passwordEncoder.encode(password);
			User newUser = username == null ?
					new User(email, encodedPassword) : new User(email, username, encodedPassword);

			return userRepository.save(newUser);
		} catch (DataIntegrityViolationException exception) {
			throw new IllegalArgumentException("이미 존재하는 아이디입니다");
		}
	}

	public User getUserByCredential(String email, String password) {
		Optional<User> userOptional = this.userRepository.findByEmail(email);

		if (userOptional.isEmpty()) {
			throw new IllegalArgumentException("해당 User가 존재하지 않습니다");
		}

		User user = userOptional.get();

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("비밀번호가 맞지 않습니다");
		}
		return user;
	}
}
