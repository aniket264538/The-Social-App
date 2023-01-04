package com.socialapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialapp.entities.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String userName);
	
}
