package com.desafio.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.entity.User;
import com.desafio.repository.UserRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public Page<User> list(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	
	public User find(int id) {
		return userRepository.findById(id);
	}
	

	public User save(User user) {
		Date today = new Date();
		user.setCreatedDate(today);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	

	public User update(User user) {
		Date today = new Date();
		user.setUpdatedDate(today);
		return userRepository.save(user);
	}
	
	public void delete(int id) {
		userRepository.deleteById(id);
	}
}
