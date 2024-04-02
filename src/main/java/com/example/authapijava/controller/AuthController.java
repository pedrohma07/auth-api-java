package com.example.authapijava.controller;

import com.example.authapijava.dtos.auth.AuthDTO;
import com.example.authapijava.interfaces.AuthServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthServiceInterface authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody AuthDTO authDTO) {

        var userAuthenticatedToken = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password());

        authenticationManager.authenticate(userAuthenticatedToken);

        return authService.getToken(authDTO);
    }
}
