package com.example.bellapizza.web;

import com.example.bellapizza.model.ShoppingCart;
import com.example.bellapizza.model.User;
import com.example.bellapizza.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService){
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }
        User user = (User) req.getSession().getAttribute("user");
        String username = user.getEmail();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("price", this.shoppingCartService.getTotalPrice(username));
        model.addAttribute("products", this.shoppingCartService.listAllPizzasInShoppingCart(shoppingCart.getId()));
        model.addAttribute("activeCart",shoppingCart);
        return "shopping-cart";
    }
    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id,
                                           HttpServletRequest req,
                                           Authentication authentication){
        try{
            User user = (User) req.getSession().getAttribute("user");
            this.shoppingCartService.addProductToShoppingCart(user.getEmail(),id);
            return "redirect:/shopping-cart";
        }catch(RuntimeException exception){
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    
}
