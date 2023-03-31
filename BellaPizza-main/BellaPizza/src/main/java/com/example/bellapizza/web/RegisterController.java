package com.example.bellapizza.web;

import com.example.bellapizza.model.User;
import com.example.bellapizza.model.exceptions.InvalidUserException;
import com.example.bellapizza.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(Model model){
        return "register";
    }

    @PostMapping()
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String surname){
        this.userService.register(name,surname,email,password,repeatPassword);
        return "redirect:/login";
    }
}
