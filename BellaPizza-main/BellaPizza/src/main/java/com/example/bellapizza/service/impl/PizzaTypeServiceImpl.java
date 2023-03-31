package com.example.bellapizza.service.impl;

import com.example.bellapizza.model.PizzaType;
import com.example.bellapizza.repository.PizzaTypeRepository;
import com.example.bellapizza.service.PizzaTypeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PizzaTypeServiceImpl implements PizzaTypeService {

    private final PizzaTypeRepository pizzaTypeRepository;

    public PizzaTypeServiceImpl(PizzaTypeRepository pizzaTypeRepository) {
        this.pizzaTypeRepository = pizzaTypeRepository;
    }

    @Override
    public Optional<PizzaType> createNew(String name, String description, String image, Integer cena) {
        if(this.pizzaTypeRepository.findFirstByName(name) != null){
            PizzaType pizzaType1 = this.pizzaTypeRepository.findFirstByName(name);
            this.pizzaTypeRepository.delete(pizzaType1);
        }
        PizzaType pizzaType = new PizzaType(name, description, image, cena);
        this.pizzaTypeRepository.save(pizzaType);
        return Optional.of(pizzaType);
    }

    @Override
    public void delete(String name) {
        PizzaType pizzaType = this.pizzaTypeRepository.findFirstByName(name);
        this.pizzaTypeRepository.delete(pizzaType);
    }
}
