package com.ddudu.todo.advice;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {
    public RestException(HttpStatus notFound, String notFoundBoard) {
    }
}
