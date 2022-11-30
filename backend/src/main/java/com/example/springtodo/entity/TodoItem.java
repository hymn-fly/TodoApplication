package com.example.springtodo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "todo_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class TodoItem {

	public TodoItem(String title, Integer userId) {
		this.title = title;
		this.userId = userId;
		this.done = false;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String title;

	@Column(columnDefinition = "TINYINT default 0")
	private boolean done;

	private Integer userId;

}
