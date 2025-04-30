package com.com.github.lucasbandeira.msagent.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HeroNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleHeroNotFound( HeroNotFoundException exception ){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), List.of());
    }
}
