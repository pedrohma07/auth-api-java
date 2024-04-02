package com.example.authapijava.dtos.user;

import com.example.authapijava.enums.UserRole;


public record ReturnUserDTO(String id, String name, String email, UserRole role) {
}
