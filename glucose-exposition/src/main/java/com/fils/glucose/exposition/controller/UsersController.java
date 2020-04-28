package com.fils.glucose.exposition.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fils.glucose.application.service.users.UserService;
import com.fils.glucose.exposition.dto.UserDto;
import com.fils.glucose.exposition.service.UserMapperService;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:2020")
public class UsersController {

	private final UserService userService;
	private final UserMapperService mapperService;

	public UsersController(UserService userService, UserMapperService mapperService) {
		this.userService = userService;
		this.mapperService = mapperService;
	}

	@PostMapping("login")
	public String login(@RequestBody UserDto userDto) {
		String username = mapperService.mapToDomainUsers(userDto).getUsername();
		return userService.login(username);
	}
}
