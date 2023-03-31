package com.example.bellapizza.model.exceptions;

public class PizzaAlreadyExistsException extends RuntimeException{
    public PizzaAlreadyExistsException(){
        super("Pizza already exists");
    }
}
