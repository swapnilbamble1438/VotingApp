package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.User;

public interface UserRepository  extends JpaRepository<User, Integer>{
	
	@Query("select v from User v where v.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
	

	
}
