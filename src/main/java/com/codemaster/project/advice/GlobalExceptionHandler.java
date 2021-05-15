package com.codemaster.project.advice;

import com.codemaster.project.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception){
        ApiError apiError = ApiError
                .builder()
                .timestamp(new Date())
                .errors(exception.getMessage())
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .build();

        HttpHeaders httpsHeaders = new HttpHeaders();
        httpsHeaders.add("Content-Type", "application/json");

        return new ResponseEntity<>(apiError, httpsHeaders, apiError.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception){
        ApiError apiError = ApiError
                .builder()
                .timestamp(new Date())
                .errors(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        HttpHeaders httpsHeaders = new HttpHeaders();
        httpsHeaders.add("Content-Type", "application/json");

        return new ResponseEntity<>(apiError, httpsHeaders, apiError.getStatus());

    }

    @ExceptionHandler(RequestBodyEmptyException.class)
    public ResponseEntity<Object> handleRequestBodyEmptyException(RequestBodyEmptyException exception){
        ApiError apiError = ApiError
                .builder()
                .timestamp(new Date())
                .errors(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        HttpHeaders httpsHeaders = new HttpHeaders();
        httpsHeaders.add("Content-Type", "application/json");

        return new ResponseEntity<>(apiError, httpsHeaders, apiError.getStatus());

    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleRequestBodyEmptyException(HttpMessageNotReadableException exception){
        ApiError apiError = ApiError
                .builder()
                .timestamp(new Date())
                .errors("Required request body is missing")
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        HttpHeaders httpsHeaders = new HttpHeaders();
        httpsHeaders.add("Content-Type", "application/json");

        return new ResponseEntity<>(apiError, httpsHeaders, apiError.getStatus());

    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Object> handleRequestBodyEmptyException(WebClientResponseException exception){
        ApiError apiError = ApiError
                .builder()
                .timestamp(new Date())
                .errors("Jira is not available")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        HttpHeaders httpsHeaders = new HttpHeaders();
        httpsHeaders.add("Content-Type", "application/json");

        return new ResponseEntity<>(apiError, httpsHeaders, apiError.getStatus());

    }

}
