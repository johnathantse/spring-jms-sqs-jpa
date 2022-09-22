package com.jms.aws.springbootjmsaws.h2jpa.example.service;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HttpsResponseService {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleException(IllegalArgumentException ex) {
        return HttpStatus.BAD_REQUEST.toString();
    }

    public <T> ResponseEntity<T> handleRequest(T responseResult) {
        if (responseResult == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(responseResult, HttpStatus.OK);
        }
    }
}
