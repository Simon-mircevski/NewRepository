package com.example.bellapizza.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "pizzatypes")
public class PizzaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long type_id;

    private String name;

    private String description;

    private String image;

    private Integer Cena;


    public PizzaType(){}

    public PizzaType(String name, String description, String image, Integer Cena) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.Cena = Cena;
    }
}
