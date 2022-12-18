package com.example.springtodo.controller;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiResponse<T> {

	private final String message;

	private final List<T> data;

	private final int statusCode;

	public static <T> ApiResponse<T> of(List<T> data) {
		return new ApiResponse<>("Success", data, 200);
	}

	public static <T> ApiResponse<T> noContent() {
		return new ApiResponse<>("Success", Collections.emptyList(), 204);
	}

}
