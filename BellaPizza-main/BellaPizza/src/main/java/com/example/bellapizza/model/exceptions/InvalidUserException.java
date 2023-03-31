package com.example.bellapizza.model.exceptions;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(){
        super("Invalid user credentials entered!");
    }
}
