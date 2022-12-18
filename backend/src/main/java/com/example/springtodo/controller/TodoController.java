package com.example.springtodo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

@RestController
@RequestMapping(path = "/todo")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;

	// TODO: 2022/11/24 Input Validation logic 추가 (Bean Validation)
	// TODO: 2022/11/24 response의 형태 맞추기(새로운 객체 생성해서 -  eg.ResponseDto) 
	@GetMapping
	public List<TodoItemResponse> listItems(@AuthenticationPrincipal String userId) {
		List<TodoItem> todoItems = todoService.listItem(Integer.valueOf(userId));

		return todoItems.stream().map(todoItem ->
			new TodoItemResponse(todoItem.getTitle(), todoItem.isDone(), todoItem.getId())
		).collect(Collectors.toList());
	}

	@PostMapping
	TodoItemResponse createItem(@AuthenticationPrincipal String userId, @Valid @RequestBody TodoItemCreateRequest request) {
		TodoItem todoItem = todoService.createItem(request.getTitle(), Integer.valueOf(userId));

		return new TodoItemResponse(todoItem.getTitle(), todoItem.isDone(), todoItem.getId());
	}

	@PutMapping(path = "/{id}")
	TodoItemResponse updateItem(@AuthenticationPrincipal String userId, @PathVariable Integer id,
		@Valid @RequestBody TodoItemUpdateRequest request) {
		TodoItem todoItem = todoService.updateItem(id, Integer.valueOf(userId), request.getTitle());

		return new TodoItemResponse(todoItem.getTitle(), todoItem.isDone(), todoItem.getId());
	}

	@DeleteMapping(path = "/{id}")
	void deleteItem(@AuthenticationPrincipal String userId, @PathVariable Integer id) {
		todoService.deleteItem(id, Integer.valueOf(userId));
	}
}
