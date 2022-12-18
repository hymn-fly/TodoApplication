package com.example.springtodo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springtodo.controller.dto.TodoItemCreateRequest;
import com.example.springtodo.controller.dto.TodoItemResponse;
import com.example.springtodo.controller.dto.TodoItemUpdateRequest;
import com.example.springtodo.entity.TodoItem;
import com.example.springtodo.service.TodoService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping(path = "/todo")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;

	@GetMapping
	public ApiResponse<TodoItemResponse> listItems(@AuthenticationPrincipal String userId) {
		List<TodoItem> todoItems = todoService.listItem(Integer.valueOf(userId));

		List<TodoItemResponse> responseBody = todoItems.stream().map(todoItem ->
			new TodoItemResponse(todoItem.getTitle(), todoItem.isDone(), todoItem.getId())
		).collect(Collectors.toList());

		return ApiResponse.of(responseBody);
	}

	@PostMapping
	public ApiResponse<TodoItemResponse> createItem(@AuthenticationPrincipal String userId, @Valid @RequestBody TodoItemCreateRequest request) {
		TodoItem todoItem = todoService.createItem(request.getTitle(), Integer.valueOf(userId));

		TodoItemResponse response = new TodoItemResponse(todoItem.getTitle(), todoItem.isDone(),
			todoItem.getId());

		return ApiResponse.of(List.of(response));
	}

	@PutMapping(path = "/{id}")
	public ApiResponse<TodoItemResponse> updateItem(@AuthenticationPrincipal String userId, @PathVariable @Positive Integer id,
		@Valid @RequestBody TodoItemUpdateRequest request) {
		TodoItem todoItem = todoService.updateItem(id, Integer.valueOf(userId), request.getTitle());

		TodoItemResponse response = new TodoItemResponse(todoItem.getTitle(), todoItem.isDone(),
			todoItem.getId());

		return ApiResponse.of(List.of(response));
	}

	@DeleteMapping(path = "/{id}")
	public ApiResponse<?> deleteItem(@AuthenticationPrincipal String userId, @PathVariable @Positive Integer id) {
		todoService.deleteItem(id, Integer.valueOf(userId));

		return ApiResponse.noContent();
	}
}
