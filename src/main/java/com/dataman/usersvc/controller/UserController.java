package com.dataman.usersvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dataman.usersvc.remote.UserService;

//@RestController
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("welcome");
		modelAndView.addObject("message","Welcome to Spring Boot & Thymeleaf.");
		
		return modelAndView;
	}
	
	@GetMapping("/user/{name}")
	public String queryUser(@RequestParam(value="name",required = true) String name) {
		
		return "user";
	}
	
	@RequestMapping("/user")
	public String userForm(ModelMap modelMap) {
//		User user = new User();
//		user.setId(1);
//		user.setUserName("yuexiaoyang");
//		user.setMobile("13012345678");
//		user.setAddress("Beijing, China");
//		model.addAttribute("user", user);
//		return "user";
//		User user = new User();
//		model.addAttribute("user", user);
//		return new ModelAndView("index");
		
		modelMap.addAttribute("welcome", userService.welcome());
		modelMap.addAttribute("redis", userService.redis());
		modelMap.addAttribute("kafka", userService.kafka());
		
		modelMap.addAttribute("users", userService.selectAll());

		return "user";
	}
	
	@RequestMapping("/")
	public String home(ModelMap modelMap) {
		modelMap.addAttribute("welcome", userService.welcome());
		modelMap.addAttribute("redis", userService.redis());
		modelMap.addAttribute("kafka", userService.kafka());
		
		return "home";
	}
}
