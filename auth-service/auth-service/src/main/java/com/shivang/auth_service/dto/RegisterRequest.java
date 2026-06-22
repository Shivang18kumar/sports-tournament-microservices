package com.shivang.auth_service.dto;

import com.shivang.auth_service.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private Role role;
}