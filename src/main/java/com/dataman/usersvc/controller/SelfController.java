package com.dataman.usersvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SelfController {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public String serviceInfo(ModelMap modelMap) {
		ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
		
		modelMap.addAttribute("serviceName", serviceInstance.getServiceId());
		
		modelMap.addAttribute("host", serviceInstance.getHost());
		modelMap.addAttribute("port", serviceInstance.getPort());
		modelMap.addAttribute("uri", serviceInstance.getUri());
		modelMap.addAttribute("metaData", serviceInstance.getMetadata());
		//modelMap.addAttribute("", serviceInstance.getHost());
		
		
		return "info";
	}
}
