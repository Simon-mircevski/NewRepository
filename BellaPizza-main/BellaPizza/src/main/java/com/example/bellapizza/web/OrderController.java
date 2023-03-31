package com.example.bellapizza.web;

import com.example.bellapizza.model.Order;
import com.example.bellapizza.model.PizzaType;
import com.example.bellapizza.model.ShoppingCart;
import com.example.bellapizza.model.User;
import com.example.bellapizza.repository.OrderRepository;
import com.example.bellapizza.service.OrderService;
import com.example.bellapizza.service.ShoppingCartService;
import com.example.bellapizza.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/placeOrder")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService, UserService userService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }
    @GetMapping
    public String getOrderPage(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Integer price = this.shoppingCartService.getTotalPrice(user.getName());
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getEmail());
        model.addAttribute("price", this.shoppingCartService.getTotalPrice(user.getEmail()));

        return "placeOrder";
    }
    @PostMapping("/add-order")
    public String PlaceOrder(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getEmail());
        List<PizzaType> pizzaTypeList = this.shoppingCartService.listAllPizzasInShoppingCart(shoppingCart.getId());
        Order order = new Order(user);
        this.orderRepository.save(order);
        return "redirect:/placeOrder/successfulOrder";
    }
    @GetMapping("/successfulOrder")
    public String successfulOrder(){
        return "successfulOrder";
    }

}
