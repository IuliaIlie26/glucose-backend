package com.fils.glucose.exposition.mappers;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.users.UserRoles;
import com.fils.glucose.domain.users.Users;
import com.fils.glucose.exposition.dto.UserDto;

@Service
public class UserMapperService {

	public Users mapToDomainUsers(UserDto dto) {
		Users user = new Users();
		user.setPassword(dto.password);
		user.setUsername(dto.username);
		if (dto.role != null) {
			user.setRole(UserRoles.valueOf(dto.role));
		}
		return user;
	}

	public UserDto mapDomainToDto(Users user) {
		UserDto dto = new UserDto();
		dto.username = user.getUsername();
		dto.role = user.getRole().toString();
		return dto;
	}
}
