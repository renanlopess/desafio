package com.desafio.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.entity.User;
import com.desafio.service.UserService;

@RestController
@RequestMapping(value="/api_rest")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
	 @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }
	
	@GetMapping(value = "/user/list", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
	public ResponseEntity<Page<User>> list(@PageableDefault(page=0, size= 10, sort= "id", direction= Sort.Direction.ASC) Pageable pageable){
		Page<User> users = userService.list(pageable);
		return new ResponseEntity<>(users, OK);
	}
	
	@GetMapping(value = "/user/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	public ResponseEntity<User> find(@PathVariable(value="id") int id) {
		User user = userService.find(id);
		return new ResponseEntity<>(user, OK);
	}
	
	@PostMapping(value = "/user/save", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	public ResponseEntity<User> save(@RequestBody User user) {		
		User userSaved = null;
    	try {
    		userSaved = userService.save(user);
    		logger.info("User saved: {}", userSaved.getLogin());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
        return new ResponseEntity<>(userSaved, OK);
	}
	
	@PutMapping(value = "/user/update", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	public ResponseEntity<User> update(@RequestBody User user) {
		User userUpdated = null;
    	try {
    		userUpdated = userService.update(user);
    		logger.info("User updated: {}", userUpdated.getLogin());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
        return new ResponseEntity<>(userUpdated, OK);
	}
	
	@DeleteMapping(value = "/user/delete/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	public void delete(@PathVariable(value="id") int id) {
		try {
    		userService.delete(id);
    		logger.info("Deleted User with id: {}", id);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
}
