package com.example.bellapizza.service.impl;

import com.example.bellapizza.model.Order;
import com.example.bellapizza.model.PizzaType;
import com.example.bellapizza.model.User;
import com.example.bellapizza.repository.OrderRepository;
import com.example.bellapizza.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> create(List<PizzaType> pizzaTypeList, User user) {
        Order order = new Order(user);
        this.orderRepository.save(order);
        return Optional.of(order);
    }
}
