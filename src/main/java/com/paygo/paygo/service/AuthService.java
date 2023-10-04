package com.paygo.paygo.service;

import org.springframework.http.ResponseEntity;

import com.paygo.paygo.dto.LoginDto;

public interface AuthService {
    
    ResponseEntity<?> signInUser(LoginDto loginDto);
}
