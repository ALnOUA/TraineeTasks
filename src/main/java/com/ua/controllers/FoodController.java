package com.ua.controllers;

import java.util.List;

import com.ua.model.Country;
import com.ua.services.CountryService;
import com.ua.services.FoodService;
import com.ua.model.Food;
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
        List<Food> listFoods = service.listAll();
        model.addAttribute("listFoods", listFoods);

        return "list_foods";
    }

	@GetMapping("/new")
	public String showNewProductPage(Model model) {
		Food food = new Food();
        List<Country> countries = countryService.listAll();
		model.addAttribute("food", food);
		model.addAttribute("countries", countries);

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
        Food food = service.get(id);
        mav.addObject("food", food);

        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/food";
    }
}
