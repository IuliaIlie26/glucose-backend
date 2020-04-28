package com.fils.glucose.application.service.users;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.domain.users.UsersRepository;
import static java.util.Objects.requireNonNull;

@Service
public class UserService {

	private final UsersRepository userRepository;

	public UserService(UsersRepository repository) {
		this.userRepository = requireNonNull(repository);
	}

	public String login(String username) {
		return userRepository.findByUsername(username).map(user -> user.getRole().getUserRole())
				.orElseThrow(() -> new TechnicalException("username.not.found"));
	}
}
