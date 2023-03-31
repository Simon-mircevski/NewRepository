package com.example.bellapizza.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    private String address;

    private Integer price;

    @ManyToMany
    private List<PizzaType> pizzaTypeList;

    @ManyToOne
    private User user;

    public Order (){}

    public Order(User user){
        this.user= user;
    }
}
