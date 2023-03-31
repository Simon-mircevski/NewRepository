package com.example.bellapizza.model.exceptions;

public class PizzaNotFoundException extends RuntimeException{
    public PizzaNotFoundException(Long id){
        super(String.format("Pizza with id: %d, not found", id));
    }
}
