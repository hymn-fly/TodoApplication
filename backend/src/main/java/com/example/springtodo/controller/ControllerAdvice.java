package com.example.springtodo.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(value={MethodArgumentNotValidException.class, ConstraintViolationException.class})
	public ApiResponse<?> handleBeanValidationException(Exception exception) {

		// example Exception Msg : ~~~~ default message [title]]; default message [내용은 비어있을 수 없습니다]]
		if (exception instanceof MethodArgumentNotValidException) {
			Pattern pattern = Pattern.compile("(default message \\[([\\w\\s가-힣]*)]]\\s$)");
			Matcher matcher = pattern.matcher(exception.getMessage());
			if (matcher.find()) {
				String exceptionMsg = matcher.group(2); // ([\w\s가-힣]*) -- group(2)
				return ApiResponse.error(exceptionMsg, HttpStatus.BAD_REQUEST);
			}
			return ApiResponse.error(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}

		// example Exception Msg : updateItem.id: id는 양수여야 합니다
		String exceptionMsg = exception.getMessage().split(":")[1];
		return ApiResponse.error(exceptionMsg, HttpStatus.BAD_REQUEST);
	}

}
