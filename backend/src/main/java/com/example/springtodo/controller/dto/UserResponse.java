package com.example.springtodo.controller.dto;

import lombok.Getter;

@Getter
public class UserResponse {

    private final String email;

    private final String username;

    private final String token;

    public UserResponse(String email, String username) {
        this.email = email;
        this.username = username;
        this.token = "";
    }

    public UserResponse(String email, String username, String token) {
        this.email = email;
        this.username = username;
        this.token = token;
    }
}
