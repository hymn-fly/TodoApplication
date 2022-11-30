package com.example.springtodo.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TodoItemResponse {

	private final String title;

	private final boolean done;

	private final int itemId;
}
