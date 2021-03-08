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
		Collection<ProductDto> listProducts= new ArrayList<>();
		listProducts.addAll(productService.listAll());
		model.addAttribute("listProducts", listProducts);
		return "list_product";
	}


	
/*	@GetMapping("/edit/{product_id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "product_id") int product_id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(product_id);
		mav.addObject("product", product);
		
		return mav;
	}*/
	
	/*@GetMapping("/delete/{product_id}")
	public String deleteProduct(@PathVariable(name = "product_id") int product_id) {
		service.delete(product_id);
		return "redirect:/product";
	}*/
}
