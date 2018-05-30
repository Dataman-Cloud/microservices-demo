package com.dataman.usersvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dataman.usersvc.remote.OrderService;

@Controller
@RequestMapping(value="/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(method=RequestMethod.GET)
	public String listAllProducts(ModelMap modelMap) {
		modelMap.addAttribute("welcome", orderService.welcome());
		modelMap.addAttribute("redis", orderService.redis());
		modelMap.addAttribute("kafka", orderService.kafka());
		
		modelMap.addAttribute("orders", orderService.selectAll());
		
		return "order";
	}
	
}
