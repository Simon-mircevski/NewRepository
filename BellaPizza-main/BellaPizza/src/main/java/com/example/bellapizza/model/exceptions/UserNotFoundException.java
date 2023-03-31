package com.example.bellapizza.model.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username){
        super(String.format("Username: %d, not found", username));
    }
}
