package org.example.ecommercebackend.exceptions;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Map<String,String> myMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=  ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            response.put(fieldName,errorMessage);
        });

        return response;
    }

}
