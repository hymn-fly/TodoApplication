package com.example.springtodo.controller;

import com.example.springtodo.controller.dto.UserCreateRequest;
import com.example.springtodo.controller.dto.UserLoginRequest;
import com.example.springtodo.controller.dto.UserResponse;
import com.example.springtodo.entity.User;
import com.example.springtodo.security.JwtTokenProvider;
import com.example.springtodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	private final JwtTokenProvider jwtService;

	@PostMapping("/signup")
	public UserResponse createUser(@RequestBody UserCreateRequest request) {
		User user = userService.createUser(request.getEmail(), request.getPassword(), request.getUsername());
		return new UserResponse(user.getEmail(), user.getUsername());
	}

	@PostMapping("/signin")
	public UserResponse userLogin(@RequestBody UserLoginRequest request) {
		User user = userService.getUserByCredential(request.getEmail(), request.getPassword());

		String token = jwtService.createToken(user.getId());

		return new UserResponse(user.getEmail(), user.getUsername(), token);
	}
}
