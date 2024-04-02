package com.example.authapijava.domain.user.dtos;

import com.example.authapijava.enums.UserRole;


public record ReturnUserDTO(String id, String name, String email, UserRole role) {
}
