package com.dataman.usersvc.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("config-server")
public interface ConfigService {
	
	@RequestMapping(value="/refresh", method=RequestMethod.POST)
	void refreshConfig();
}
