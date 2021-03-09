package com.ua.controllers;

import com.ua.model.Country;
import com.ua.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService service;

    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("listCountries", service.listAll());
        return "list_countries";
    }

    @GetMapping("/new")
    public String showNewCountryPage(Model model) {
        model.addAttribute("country", new Country());
        return "new_country";
    }

    @PostMapping("/save")
    public String saveCountry(@ModelAttribute("currency") Country country) {
        service.save(country);
        return "redirect:/country";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditCountryPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_country");
        mav.addObject("country", service.get(id));
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable(name = "id") long id) {
        service.delete(id);
        return "redirect:/country";
    }
}
