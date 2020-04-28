package com.fils.glucose.exposition.service;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.users.Users;
import com.fils.glucose.exposition.dto.UserDto;

@Service
public class UserMapperService {

	public Users mapToDomainUsers(UserDto dto) {
		return new Users(dto.username, dto.password);
	}

	public UserDto mapDomainToDto(Users user) {
		UserDto dto = new UserDto();
		dto.username = user.getUsername();
		dto.role = user.getRole().getUserRole();
		return dto;
	}
}
