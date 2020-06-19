package com.fils.glucose.application.service.users;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.domain.personal.information.admin.Admin;
import com.fils.glucose.domain.personal.information.admin.AdminRepository;
import com.fils.glucose.domain.users.Users;
import com.fils.glucose.domain.users.UsersRepository;
import static java.util.Objects.requireNonNull;

@Service
public class UserService {

	private final UsersRepository userRepository;
	private final AdminRepository adminRepo;

	public UserService(UsersRepository repository, AdminRepository adminRepo) {
		this.userRepository = requireNonNull(repository);
		this.adminRepo = adminRepo;
	}

	public String login(String username) {
		return userRepository.findByUsername(username).map(Users::getRole)
				.orElseThrow(() -> new TechnicalException("username.not.found"));
	}

	public String getAdminNameById(String username) {
		Admin admin = adminRepo.findByUsername(username).orElseThrow(() -> new TechnicalException("admin.not.found"));
		return admin.getLastname() + " " + admin.getName();
	}
}
