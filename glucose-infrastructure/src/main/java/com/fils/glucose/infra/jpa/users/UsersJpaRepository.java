package com.fils.glucose.infra.jpa.users;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.fils.glucose.domain.users.Users;
import com.fils.glucose.domain.users.UsersRepository;

@Repository
public class UsersJpaRepository implements UsersRepository {

	private final IUsersRepository usersRepository;

	public UsersJpaRepository(IUsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	public Optional<Users> findByUsername(String username) {
		return usersRepository.findByUsername(username);
	}

	@Override
	public void save(Users user) {
		usersRepository.save(user);
	}
}
