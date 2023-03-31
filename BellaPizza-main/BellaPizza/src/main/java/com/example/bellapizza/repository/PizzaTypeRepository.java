package com.example.bellapizza.repository;

import com.example.bellapizza.model.PizzaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaTypeRepository extends JpaRepository<PizzaType, Long> {
    PizzaType findFirstByName(String name);
}
