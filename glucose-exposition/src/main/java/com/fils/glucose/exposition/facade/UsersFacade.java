package com.fils.glucose.exposition.facade;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.service.users.UserService;
import com.fils.glucose.domain.users.Users;
import com.fils.glucose.exposition.dto.UserDto;
import com.fils.glucose.exposition.mappers.UserMapperService;

@Service
public class UsersFacade {

	private final UserService userService;
	private final UserMapperService userMapperService;

	public UsersFacade(UserService userService, UserMapperService userMapperService) {
		this.userService = userService;
		this.userMapperService = userMapperService;
	}

	public String login(UserDto userDto) {
		Users user = userMapperService.mapToDomainUsers(userDto);
		return userService.login(user);
	}

	public String getAdminNameById(String username) {
		return userService.getAdminNameById(username);
	}

	public void changePassword(UserDto userDto) {
		Users user = userMapperService.mapToDomainUsers(userDto);
		userService.changePassword(user);
	}
}
