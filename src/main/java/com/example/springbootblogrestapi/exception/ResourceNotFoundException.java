package com.example.springbootblogrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND) //respond with the specified HTTP status code
public class ResourceNotFoundException extends RuntimeException{
    //post with a given ID not found
    private String resourceName;
    private String fieldName;
    private Long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue)); // Post not found with Id: 1
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public String getResourceName(){
        return resourceName;
    }
    public String getFieldName(){
        return fieldName;
    }
    public Long getFieldValue(){
        return fieldValue;
    }
}
