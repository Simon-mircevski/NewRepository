package com.example.bellapizza.repository;

import com.example.bellapizza.model.ShoppingCart;
import com.example.bellapizza.model.User;
import com.example.bellapizza.model.enumeration.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}

