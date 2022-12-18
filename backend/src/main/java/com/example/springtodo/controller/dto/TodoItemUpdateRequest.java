package com.example.springtodo.controller.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TodoItemUpdateRequest {

	@NotBlank
	private String title;
}
