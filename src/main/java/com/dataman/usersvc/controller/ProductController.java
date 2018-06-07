package com.dataman.usersvc.controller;

import java.util.Date;

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
		
		StringBuilder sbLogs = new StringBuilder();
		String welcome;
		String kafka;
		String redis;		
		
		sbLogs.append(new Date()).append("\t").append("call DBACCESS-SERVICE——>welcome()").append("\n");
		welcome = productService.welcome();
		sbLogs.append("[Return Message]\t").append(welcome).append("\n");
		sbLogs.append(new Date()).append("\t").append("finished call DBACCESS-SERVICE——>welcome()").append("\n");
		
		sbLogs.append(new Date()).append("\t").append("call DBACCESS-SERVICE——>redis()").append("\n");
		redis = productService.redis();
		sbLogs.append("[Return Message]\t").append(redis).append("\n");
		sbLogs.append(new Date()).append("\t").append("finished call DBACCESS-SERVICE——>redis()").append("\n");
		
		sbLogs.append(new Date()).append("\t").append("call DBACCESS-SERVICE——>kafka()").append("\n");
		kafka = productService.kafka();
		sbLogs.append("[Return Message]\t").append(kafka).append("\n");
		sbLogs.append(new Date()).append("\t").append("finished call DBACCESS-SERVICE——>kafka()").append("\n");
		
		modelMap.addAttribute("welcome", welcome);
		modelMap.addAttribute("redis", redis);
		modelMap.addAttribute("kafka", kafka);
		
		sbLogs.append(new Date()).append("\t").append("call DBACCESS-SERVICE——>selectAllProducts()").append("\n");
		modelMap.addAttribute("orders", productService.selectAll());
		sbLogs.append(new Date()).append("\t").append("finished call DBACCESS-SERVICE——>selectAll()").append("\n");
		
		modelMap.addAttribute("logs", sbLogs.toString());
		
		return "product";
	}
	
}
