package com.ua.controllers;

import java.util.List;

import com.ua.services.CurrencyService;
import com.ua.model.Currency;
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
        List<Currency> listCurrencies = service.listAll();
        model.addAttribute("listCurrencies", listCurrencies);

        return "list_currencies";
    }

    @GetMapping("/new")
    public String showNewCurrencyPage(Model model) {
        Currency currency = new Currency();
        model.addAttribute("currency", currency);

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
        Currency currency = service.get(id);
        mav.addObject("currency", currency);

        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteCurrency(@PathVariable(name = "id") long id) {
        service.delete(id);
        return "redirect:/currency";
    }
}
