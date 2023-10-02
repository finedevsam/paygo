package com.paygo.paygo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.paygo.paygo.entity.User;
import com.paygo.paygo.repository.UserRepository;

@Service
public class AuthenticatedUser {
    
    @Autowired
    private UserRepository userRepository;


    public User auth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        return userRepository.findUserByEmail(authentication.getName());
    }
}
