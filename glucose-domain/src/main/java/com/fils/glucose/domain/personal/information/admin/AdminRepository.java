package com.fils.glucose.domain.personal.information.admin;

import java.util.Optional;

public interface AdminRepository {

	public Optional<Admin> findByUsername(String username);
}
