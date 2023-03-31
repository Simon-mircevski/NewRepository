package com.example.bellapizza.model;

import com.example.bellapizza.model.enumeration.Role;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String address;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(){}

    public User(String email, String password, String name, String surname, Role role){
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role= role;
    }
}
