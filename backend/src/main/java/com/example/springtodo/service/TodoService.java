package com.example.springtodo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springtodo.entity.TodoItem;
import com.example.springtodo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

	private final TodoRepository todoRepository;

	public TodoItem createItem(String title, Integer userId) {
		return todoRepository.save(new TodoItem(title, userId));
	}

	@Transactional(readOnly = true)
	public List<TodoItem> listItem(Integer userId) {
		return todoRepository.findByUserId(userId);
	}

	public TodoItem updateItem(Integer itemId, Integer userId, String updateTitle) {
		TodoItem todoItem = getTodoItem(itemId, userId);

		todoItem.setTitle(updateTitle);
		return todoItem;
	}

	public void deleteItem(Integer itemId, Integer userId) {
		TodoItem todoItem = getTodoItem(itemId, userId);

		todoRepository.delete(todoItem);
	}

	private TodoItem getTodoItem(Integer itemId, Integer userId) {
		// TODO: 2022/11/24 이걸 에러로 띄우는게 맞을지. Optional에서 값없으면 그냥 아무 로직 처리 안하는 방법도 존재함
		Optional<TodoItem> itemOptional = todoRepository.findByIdAndUserId(itemId, userId);
		if (itemOptional.isEmpty()) {
			throw new IllegalArgumentException("Item을 찾을 수 없습니다.");
		}
		return itemOptional.get();
	}
}
