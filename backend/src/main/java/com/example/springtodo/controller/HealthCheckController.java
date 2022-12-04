package com.example.springtodo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

	@GetMapping("/")
	public String ping() {
		return "The service is up and running ...";
	}
}
