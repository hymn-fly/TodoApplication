package com.example.springtodo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiResponse<T> {

	private final String message;

	private final List<T> data;

	private final int statusCode;

	public static <T> ApiResponse<T> of(List<T> data) {
		return new ApiResponse<>("Success", data, HttpStatus.OK.value());
	}

	public static <T> ApiResponse<T> noContent() {
		return new ApiResponse<>("Success", Collections.emptyList(), HttpStatus.NO_CONTENT.value());
	}

	public static <T> ApiResponse<T> error(String message, HttpStatus status) {
		return new ApiResponse<>(message, Collections.emptyList(), status.value());
	}

}
