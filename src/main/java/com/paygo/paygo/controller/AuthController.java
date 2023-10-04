package com.paygo.paygo.controller;

import com.paygo.paygo.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.paygo.paygo.dto.LoginDto;

@RestController
@RequestMapping("auth/")
public class AuthController {
    
    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("login")
    public ResponseEntity<?>login(@RequestBody LoginDto loginDto){
        return authService.signInUser(loginDto);
    }
}
