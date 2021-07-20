package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findById(int id);
	
	User findByLogin(String login);

}
