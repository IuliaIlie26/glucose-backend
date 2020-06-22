package com.fils.glucose.application.service.users;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.encryption.AESEncryption;
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
	private final AESEncryption encryptionService;

	public UserService(UsersRepository repository, AdminRepository adminRepo, AESEncryption encryptionService) {
		this.userRepository = requireNonNull(repository);
		this.adminRepo = adminRepo;
		this.encryptionService = encryptionService;
	}

	public String login(Users user) {
		Users userFromDb = userRepository.findByUsername(user.getUsername())
				.orElseThrow(() -> new TechnicalException("backend.username.not.found"));

		String password = encryptionService.decrypt(userFromDb.getPassword());
		if (user.getPassword().equals(password)) {
			return userFromDb.getRole().toString();
		} else {
			throw new TechnicalException("backend.wrong.password.or.email");
		}

	}

	public String getAdminNameById(String username) {
		Admin admin = adminRepo.findByUsername(username)
				.orElseThrow(() -> new TechnicalException("backend.admin.not.found"));
		return admin.getLastname() + " " + admin.getName();
	}

	public void changePassword(Users user) {
		String password = encryptionService.encrypt(user.getPassword());
		user.setPassword(password);
		this.userRepository.save(user);
	}
}
