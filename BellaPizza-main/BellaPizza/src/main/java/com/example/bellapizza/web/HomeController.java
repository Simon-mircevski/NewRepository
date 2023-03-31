package com.example.bellapizza.web;

import com.example.bellapizza.model.PizzaType;
import com.example.bellapizza.model.enumeration.Role;
import com.example.bellapizza.repository.PizzaTypeRepository;
import com.example.bellapizza.service.PizzaTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final PizzaTypeRepository pizzaTypeRepository;
    private final PizzaTypeService pizzaTypeService;

    public HomeController(PizzaTypeRepository pizzaTypeRepository, PizzaTypeService pizzaTypeService) {
        this.pizzaTypeRepository = pizzaTypeRepository;
        this.pizzaTypeService = pizzaTypeService;
    }

    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("pizzas",pizzaTypeRepository.findAll());
        model.addAttribute("userRole", Role.USER);
        model.addAttribute("adminRole", Role.ADMIN);
        return "home";
    }
    @GetMapping("/{id}/view")
    public String viewPizza(@PathVariable Long id,Model model ){
        PizzaType pizzaType = this.pizzaTypeRepository.findById(id).get();
        model.addAttribute("selectedPizza", pizzaType);
        model.addAttribute("userrole", Role.USER);
        model.addAttribute("adminRole", Role.ADMIN);
        return "view";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String addPizza(Model model){
        return "addPizzaType";
    }

    @PostMapping("/add")
    public String savePizza(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam String image,
                            @RequestParam Integer cena){
        this.pizzaTypeService.createNew(name, description,image,cena);
        return "redirect:/home";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePizza(@PathVariable Long id){
        this.pizzaTypeRepository.deleteById(id);
        return "redirect:/home";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editPizza(@PathVariable Long id, Model model){
        if(this.pizzaTypeRepository.findById(id).isPresent()){
            PizzaType pizzaType = this.pizzaTypeRepository.findById(id).get();
            model.addAttribute("selectedPizza", pizzaType);
            return "addPizzaType";
        }
        return "redirect:/home?error=PizzaNotFound";
    }
}
