package com.dataman.usersvc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataman.usersvc.remote.ConfigService;
import com.dataman.usersvc.remote.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConfigService configService;
	
	@RequestMapping("/")
	public String home(ModelMap modelMap) {
		StringBuilder sbLogs = new StringBuilder();
		String welcome;
		String kafka;
		String redis;
		
		
		sbLogs.append(new Date()).append("\t").append("call RIBBON-PROVIDER——>welcome()").append("\n");
		welcome = userService.welcome();
		sbLogs.append("[Return Message]\t").append(welcome).append("\n");
		sbLogs.append(new Date()).append("\t").append("finished call RIBBON-PROVIDER——>welcome()").append("\n");
		
		sbLogs.append(new Date()).append("\t").append("call RIBBON-PROVIDER——>redis()").append("\n");
		redis = userService.redis();
		sbLogs.append("[Return Message]\t").append(redis).append("\n");
		sbLogs.append(new Date()).append("\t").append("finished call RIBBON-PROVIDER——>redis()").append("\n");
		
		sbLogs.append(new Date()).append("\t").append("call RIBBON-PROVIDER——>kafka()").append("\n");
		kafka = userService.kafka();
		sbLogs.append("[Return Message]\t").append(kafka).append("\n");
		sbLogs.append(new Date()).append("\t").append("finished call RIBBON-PROVIDER——>kafka()").append("\n");
		
		modelMap.addAttribute("welcome", welcome);
		modelMap.addAttribute("redis", redis);
		modelMap.addAttribute("kafka", kafka);
		modelMap.addAttribute("logs", sbLogs.toString());
		
		return "home";
	}
	
	public String refresh(ModelMap modelMap) {
		//调用config-server的refresh接口
		configService.refreshConfig();
		
		modelMap.addAttribute("welcome", userService.welcome());
		modelMap.addAttribute("redis", userService.redis());
		modelMap.addAttribute("kafka", userService.kafka());
		
		return "home";
	}
}
