package com.example.springtodo.controller.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TodoItemUpdateRequest {

	@NotBlank(message = "내용은 비어있을 수 없습니다")
	private String title;
}
