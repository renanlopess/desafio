package com.desafio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.desafio.entity.User;
import com.desafio.repository.UserRepository;

@Repository
public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Not Found");
		}else {
			return user;
		}
	}
		

}
