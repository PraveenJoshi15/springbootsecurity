package com.secure.app.securitylearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secure.app.securitylearning.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String name);

}
