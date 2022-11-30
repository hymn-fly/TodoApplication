package com.example.springtodo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springtodo.entity.TodoItem;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Integer> {

	Optional<TodoItem> findByIdAndUserId(Integer id, Integer userId);

	List<TodoItem> findByUserId(Integer userId);
}
