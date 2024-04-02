package com.example.authapijava.dtos.user;
import com.example.authapijava.enums.UserRole;
import java.util.Date;

public record UpdateUserDTO(String name, String password, UserRole role, Date updatedAt) {
}
