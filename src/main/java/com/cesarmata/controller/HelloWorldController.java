/**
 * 
 */
package com.cesarmata.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cesarmata.dto.User;
import com.cesarmata.exceptions.UserNotFoundException;
import com.cesarmata.service.UserService;

/**
 * @author Cesar Mata 
 *
 */
@RestController
public class HelloWorldController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path= "/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id-" +id);
		}	
		// HETOAS concept.
		// All users.
//		Resource<User> resource = new Resource<User>(user);
//		
//		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//		
//		resource.add(linkTo.withRel("all-users"));
		
		return user;
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		
		if(user==null) {
			throw new UserNotFoundException("id-" +id);
		}
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}