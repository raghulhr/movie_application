package org.alaigal.ratingsdataservice.advice;

import org.alaigal.ratingsdataservice.exception.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentException(MethodArgumentNotValidException exception){
        Map<String,String> map = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->{
            map.put(error.getField(), error.getDefaultMessage());
        });
        return map;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(MovieNotFoundException.class)
    public Map<String,String > movieNotFoundException(MovieNotFoundException exception){
        Map<String,String> map = new HashMap<>();
        map.put("errorMessage",exception.getMessage());
        return map;
    }
}
