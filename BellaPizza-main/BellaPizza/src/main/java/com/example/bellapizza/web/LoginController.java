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

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getLoginPage(Model model, HttpServletRequest request){
        return "login";
    }

    @PostMapping()
    public String postLogin(@RequestParam String email,
                            @RequestParam String password,
                            Model model,
                            HttpServletRequest request){
        User user = null;
        try{
            user = this.userService.login(email,password);
            request.getSession().setAttribute("user",user);
            return "redirect:/home";
        }
        catch(InvalidUserException exception){
            model.addAttribute("hasErrors", true);
            model.addAttribute("error",exception.getMessage());
            return "login";
        }
    }
}
