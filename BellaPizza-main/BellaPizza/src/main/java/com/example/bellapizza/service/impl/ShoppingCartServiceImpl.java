package com.example.bellapizza.service.impl;

import com.example.bellapizza.model.PizzaType;
import com.example.bellapizza.model.ShoppingCart;
import com.example.bellapizza.model.User;
import com.example.bellapizza.model.enumeration.ShoppingCartStatus;
import com.example.bellapizza.model.exceptions.PizzaNotFoundException;
import com.example.bellapizza.model.exceptions.ProductAlreadyInCart;
import com.example.bellapizza.model.exceptions.ShoppingCartNotFound;
import com.example.bellapizza.repository.PizzaTypeRepository;
import com.example.bellapizza.repository.ShoppingCartRepository;
import com.example.bellapizza.repository.UserRepository;
import com.example.bellapizza.service.PizzaTypeService;
import com.example.bellapizza.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final PizzaTypeService pizzaTypeService;
    private final PizzaTypeRepository pizzaTypeRepository;
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   PizzaTypeService pizzaTypeService, PizzaTypeRepository pizzaTypeRepository){
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.pizzaTypeService = pizzaTypeService;
        this.pizzaTypeRepository = pizzaTypeRepository;
    }
    @Override
    public List<PizzaType> listAllPizzasInShoppingCart(Long cartID) {
        if(!this.shoppingCartRepository.findById(cartID).isPresent())
            throw new ShoppingCartNotFound(cartID);

        return this.shoppingCartRepository.findById(cartID).get().getPizzaTypes();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByEmail(username);
        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED).orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        PizzaType pizzaType = this.pizzaTypeRepository.findById(productId)
                .orElseThrow(()-> new PizzaNotFoundException(productId));
        if(shoppingCart.getPizzaTypes()
                .stream().filter(i->i.getType_id().equals(productId))
                .collect(Collectors.toList()).size()>0)
            throw new ProductAlreadyInCart(productId,username);
        shoppingCart.getPizzaTypes().add(pizzaType);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Integer getTotalPrice(String username) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        List<PizzaType> list = this.listAllPizzasInShoppingCart(shoppingCart.getId());
        Integer fullPrice = 0;
        for(int i = 0; i<list.size();i++){
            fullPrice += list.get(i).getCena();
        }
        return fullPrice;
    }
}
