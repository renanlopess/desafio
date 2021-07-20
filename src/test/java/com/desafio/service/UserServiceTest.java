package com.desafio.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafio.entity.User;
import com.desafio.repository.UserRepository;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
	 	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;

	private User userTest;
	
	@BeforeEach
	public void setUp() {
		RestAssuredMockMvc.standaloneSetup(this.userService);		
		userTest = new User();
		Date today = new Date();
		userTest.setId(107);
		userTest.setCreatedDate(today);
		userTest.setUpdatedDate(today);
		userTest.setNome("test");
		userTest.setLogin("test");
		userTest.setEmail("test");
		userTest.setPassword("test");
		userTest.setAdmin(true);
	}
	
	@Test
	void createUserSuccess() {
		when(userRepository.save(userTest)).thenReturn(userTest);
		assertThat(userService.save(userTest)).isEqualTo(userTest);
	}

	@Test
	void updateUserSuccess() {
		when(userRepository.save(userTest)).thenReturn(userTest);
		assertThat(userService.update(userTest)).isEqualTo(userTest);
	}
	
	@Test
	void findUserSuccess(){
		when(userRepository.findById(107)).thenReturn(userTest);
		assertThat(userService.find(107)).isEqualTo(userTest);
	}
	
}
