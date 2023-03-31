package com.example.bellapizza.model.exceptions;

public class ShoppingCartNotFound extends RuntimeException{
    public ShoppingCartNotFound(Long id){
        super(String.format("Cart with id: %d, not found", id));
    }
}
