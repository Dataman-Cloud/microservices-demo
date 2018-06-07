package com.dataman.usersvc.controller;

import java.util.Date;

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

		StringBuilder sbLogs = new StringBuilder();
		String welcome;
		String kafka;
		String redis;		
		
		sbLogs.append(new Date()).append("\t").append("call DBACCESS-SERVICE——>welcome()").append("\n");
		welcome = userService.welcome();
		sbLogs.append("[Return Message]\t").append(welcome).append("\n");
		sbLogs.append(new Date()).append("\t").append("finished call DBACCESS-SERVICE——>welcome()").append("\n");
		
		sbLogs.append(new Date()).append("\t").append("call DBACCESS-SERVICE——>redis()").append("\n");
		redis = userService.redis();
		sbLogs.append("[Return Message]\t").append(redis).append("\n");
		sbLogs.append(new Date()).append("\t").append("finished call DBACCESS-SERVICE——>redis()").append("\n");
		
		sbLogs.append(new Date()).append("\t").append("call DBACCESS-SERVICE——>kafka()").append("\n");
		kafka = userService.kafka();
		sbLogs.append("[Return Message]\t").append(kafka).append("\n");
		sbLogs.append(new Date()).append("\t").append("finished call DBACCESS-SERVICE——>kafka()").append("\n");
		
		modelMap.addAttribute("welcome", welcome);
		modelMap.addAttribute("redis", redis);
		modelMap.addAttribute("kafka", kafka);
		
		sbLogs.append(new Date()).append("\t").append("call DBACCESS-SERVICE——>selectAllUsers()").append("\n");
		modelMap.addAttribute("orders", userService.selectAll());
		sbLogs.append(new Date()).append("\t").append("finished call DBACCESS-SERVICE——>selectAll()").append("\n");
		
		modelMap.addAttribute("logs", sbLogs.toString());

		return "user";
	}
}
