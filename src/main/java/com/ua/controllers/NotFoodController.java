package com.ua.controllers;

import java.util.List;

import com.ua.model.NotFood;
import com.ua.services.NotFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notfood")
public class NotFoodController {

    @Autowired
    private NotFoodService service;

    @GetMapping("")
    public String viewProductPage(Model model) {
        List<NotFood> listNotFoods = service.listAll();
        model.addAttribute("listNotFoods", listNotFoods);

        return "list_notfoods";
    }

    @GetMapping("/new")
    public String showNewProductPage(Model model) {
        NotFood notFood = new NotFood();
        model.addAttribute("notfood", notFood);

        return "new_notfood";
    }

    @PostMapping(value = "/save")
    public String saveProduct(@ModelAttribute("food") NotFood notFood) {
        service.save(notFood);

        return "redirect:/notfood";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_notfood");
        NotFood notFood = service.get(id);
        mav.addObject("notfood", notFood);

        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/notfood";
    }
}
