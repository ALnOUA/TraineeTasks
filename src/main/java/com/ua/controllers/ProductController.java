package com.ua.controllers;

import java.util.ArrayList;
import java.util.Collection;

import com.ua.dto.ProductDto;
import com.ua.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {


	@Autowired
	private ProductService productService;

	@GetMapping("")
	public String viewProductPage(Model model) {
		model.addAttribute("listProducts", productService.listAll());
		return "list_product";
	}
}
