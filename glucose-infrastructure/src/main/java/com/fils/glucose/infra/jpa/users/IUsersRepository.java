package com.fils.glucose.infra.jpa.users;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fils.glucose.domain.users.Users;

public interface IUsersRepository extends JpaRepository<Users, Long>{
	
	Optional<Users> findByUsername(String username);
}
