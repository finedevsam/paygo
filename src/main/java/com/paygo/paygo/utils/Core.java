package com.paygo.paygo.utils;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Core {
    public String getCif(){
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        return String.format("%06d", randomNumber);
    }

    public String getCode(){
        Random random = new Random();
        int randomNumber = random.nextInt(9999);
        return String.format("%04d", randomNumber);
    }
}
