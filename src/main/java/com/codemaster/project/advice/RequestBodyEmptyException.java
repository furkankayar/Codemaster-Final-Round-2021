package com.codemaster.project.advice;

public class RequestBodyEmptyException extends IllegalArgumentException{

   public RequestBodyEmptyException(String message){
        super(message);
    }
}
