package com.example.bellapizza.service;

import com.example.bellapizza.model.PizzaType;

import java.util.Optional;

public interface PizzaTypeService {
    Optional<PizzaType> createNew(String name, String description, String image,Integer cena);
    void delete(String name);
}
