package com.jayian.businesscard.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception e) {
        e.printStackTrace();
        return "/error";
    }
}
