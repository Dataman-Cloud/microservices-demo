package com.dataman.usersvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dataman.usersvc.remote.ProductService;

@Controller
@RequestMapping(value="/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(method=RequestMethod.GET)
	public String listAllProducts(ModelMap modelMap) {
		modelMap.addAttribute("welcome", productService.welcome());
		modelMap.addAttribute("redis", productService.redis());
		modelMap.addAttribute("kafka", productService.kafka());
		
		modelMap.addAttribute("products", productService.selectAll());
		
		return "product";
	}
	
}
