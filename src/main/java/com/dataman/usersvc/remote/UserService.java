package com.dataman.usersvc.remote;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dataman.usersvc.entity.User;

@FeignClient("dbaccess-service")
public interface UserService {
	
	@RequestMapping("/user/selectAll")
	List<User> selectAll();
	
	@RequestMapping("/ribbon/welcome")
	String welcome();

	@RequestMapping("/ribbon/redis")
	String redis();
	
	@RequestMapping("/ribbon/kafka")
	String kafka();
}
