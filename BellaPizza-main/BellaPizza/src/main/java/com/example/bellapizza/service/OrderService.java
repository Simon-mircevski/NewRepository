package com.example.bellapizza.service;

import com.example.bellapizza.model.Order;
import com.example.bellapizza.model.PizzaType;
import com.example.bellapizza.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> create(List<PizzaType> pizzaTypeList, User user);
}
