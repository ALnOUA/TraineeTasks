package com.ua.controllers;

import com.ua.model.Currency;
import com.ua.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("listCurrencies", service.listAll());
        return "list_currencies";
    }

    @GetMapping("/new")
    public String showNewCurrencyPage(Model model) {
        model.addAttribute("currency", new Currency());
        return "new_currency";
    }

    @PostMapping("/save")
    public String saveCurrency(@ModelAttribute("currency") Currency currency) {
        service.save(currency);
        return "redirect:/currency";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditCurrencyPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_currency");
        mav.addObject("currency", service.get(id));
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteCurrency(@PathVariable(name = "id") long id) {
        service.delete(id);
        return "redirect:/currency";
    }
}
