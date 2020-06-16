package com.fils.glucose.domain.users;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository {
	Optional<Users> findByUsername(String username);

	void save(Users users);
}
