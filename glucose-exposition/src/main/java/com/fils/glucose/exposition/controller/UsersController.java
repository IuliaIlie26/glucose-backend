package com.fils.glucose.exposition.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fils.glucose.application.service.users.UserService;
import com.fils.glucose.exposition.dto.UserDto;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:2020")
public class UsersController {

	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("login")
	public String login(@RequestBody UserDto userDto) {
		return userService.login(userDto.username);
	}
	
	@GetMapping("getAdminNameById")
	public String getAdminNameById(@RequestParam String username){
		return userService.getAdminNameById(username);
	}
}
