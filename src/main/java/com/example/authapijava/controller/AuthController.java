package com.example.authapijava.controller;

import com.example.authapijava.dtos.auth.AuthDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody AuthDTO authDTO) {
        return "Login";
    }
}
