package com.example.authapijava.interfaces;

import com.example.authapijava.dtos.auth.AuthDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthServiceInterface extends UserDetailsService {
    public String getToken(AuthDTO authDTO);

    public String validateToken(String token);

}

