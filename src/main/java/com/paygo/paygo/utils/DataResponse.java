package com.paygo.paygo.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataResponse {

    public ResponseEntity<?> dataResponse(String statusCode, String status, Object response, HttpStatus httpStatus){
        Map<String, Object> data = new HashMap<>();
        data.put("statusCode", statusCode);
        data.put("status", status);
        data.put("statusResponse", response);
        return new ResponseEntity<>(data, httpStatus);
    }

}
