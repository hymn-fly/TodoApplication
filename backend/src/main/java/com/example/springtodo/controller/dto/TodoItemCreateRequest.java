package com.example.springtodo.controller.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoItemCreateRequest {

	@NotBlank
	private String title;

}
