package com.fils.glucose.infra.jpa.personal.information.users;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fils.glucose.domain.personal.information.admin.Admin;
import com.fils.glucose.domain.personal.information.admin.AdminRepository;

@Repository
public class AdminJpaRepository implements AdminRepository{

	private final IAdminRepository adminRepo;
	
	public AdminJpaRepository(IAdminRepository adminRepo) {
		this.adminRepo = adminRepo;
	}


	@Override
	public Optional<Admin> findByUsername(String username) {
		return adminRepo.findByUsername(username);
	}

}
