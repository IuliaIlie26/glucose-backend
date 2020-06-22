package com.fils.glucose.exposition.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fils.glucose.exposition.dto.UserDto;
import com.fils.glucose.exposition.facade.UsersFacade;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:2020")
public class UsersController {

	private final UsersFacade usersFacade;

	public UsersController(UsersFacade usersFacade) {
		this.usersFacade = usersFacade;
	}

	@PostMapping("login")
	public String login(@RequestBody UserDto userDto) {
		return usersFacade.login(userDto);

	}

	@GetMapping("getAdminNameById")
	public String getAdminNameById(@RequestParam String username) {
		return usersFacade.getAdminNameById(username);
	}
	
	@PostMapping("changePassword")
	public void changePassword(@RequestBody UserDto userDto) {
		usersFacade.changePassword(userDto);
	}
}
