package com.fils.glucose.infra.jpa.personal.information.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fils.glucose.domain.personal.information.admin.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Long> {
	
	public Optional<Admin> findByUsername(String username);
}
