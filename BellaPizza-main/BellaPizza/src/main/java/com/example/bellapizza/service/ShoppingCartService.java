package com.example.bellapizza.service;

import com.example.bellapizza.model.PizzaType;
import com.example.bellapizza.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<PizzaType> listAllPizzasInShoppingCart(Long cartID);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username,Long productId);
    Integer getTotalPrice(String username);
}
