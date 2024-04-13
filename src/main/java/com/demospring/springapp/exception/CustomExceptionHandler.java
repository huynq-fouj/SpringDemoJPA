package com.demospring.springapp.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    @ResponseStatus
    public Object handleRequestValidationException(
        Exception ex,
        HttpServletRequest request
    ) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
         
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseBody.put("error", ex.getMessage());
        responseBody.put("path", request.getServletPath());
         
        return responseBody;
    }

}
