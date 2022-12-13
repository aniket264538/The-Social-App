package com.socialapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialapp.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
	
}
