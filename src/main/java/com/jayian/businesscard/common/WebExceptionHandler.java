package com.jayian.businesscard.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception e) {
        e.printStackTrace();
        return "/error";
    }
}
