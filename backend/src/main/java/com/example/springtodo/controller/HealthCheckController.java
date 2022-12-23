package com.example.springtodo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

	@GetMapping("/")
	public String ping(@AuthenticationPrincipal OAuth2User principal) {
		return String.format("The service is up and running... %s", principal);
	}
}
