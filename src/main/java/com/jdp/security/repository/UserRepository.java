package com.jdp.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jdp.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByName(String username);
	
}
