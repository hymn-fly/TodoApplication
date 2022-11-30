package com.example.springtodo.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserCreateRequest {

    private String username;

    private String email;

    private String password;
}
