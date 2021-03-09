package com.ua.controllers;

import com.ua.model.Food;
import com.ua.services.CountryService;
import com.ua.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService service;
    @Autowired
    private CountryService countryService;

    @GetMapping("")
    public String viewProductPage(Model model) {
        model.addAttribute("listFoods", service.listAll());
        return "list_foods";
    }

	@GetMapping("/new")
	public String showNewProductPage(Model model) {
		model.addAttribute("food", new Food());
		model.addAttribute("countries", countryService.listAll());
		return "new_food";
	}

    @PostMapping(value = "/save")
    public String saveProduct(@ModelAttribute("food") Food food) {
        service.save(food);
        return "redirect:/food";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_food");
        mav.addObject("food", service.get(id));

        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/food";
    }
}
