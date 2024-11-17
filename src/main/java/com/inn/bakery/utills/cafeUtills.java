package com.inn.bakery.utills;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class cafeUtills {

    private cafeUtills() {

    }
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return  new ResponseEntity<String>( "{\"message\":\""+ responseMessage+ "\"}", httpStatus);
    }
}