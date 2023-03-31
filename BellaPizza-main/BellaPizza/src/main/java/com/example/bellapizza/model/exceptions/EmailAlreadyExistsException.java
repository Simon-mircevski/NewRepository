package com.example.bellapizza.model.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email){
        super(String.format("Email: %d, already exists", email));
    }
}
