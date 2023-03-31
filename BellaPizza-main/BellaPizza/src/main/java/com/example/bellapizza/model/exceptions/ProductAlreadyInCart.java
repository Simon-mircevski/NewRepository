package com.example.bellapizza.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInCart extends RuntimeException{
    public ProductAlreadyInCart(Long id, String username){
        super(String.format("Product with id: %d, already in cart for User: %s",id,username));
    }
}