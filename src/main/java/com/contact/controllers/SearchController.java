package com.contact.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.contact.entities.Contact;
import com.contact.entities.User;
import com.contact.repo.ContactRepository;
import com.contact.repo.UserRepository;

//Here we are not going to return any kind of view or page..It will return JSON 

@RestController
public class SearchController {

	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value = "/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query ,Principal principal){
		System.out.println(query);
		String name = principal.getName(); //if we test it by postman,then we will not get the current user.
		User user = this.userRepository.getUserByUserName(name);
		List<Contact> contacts = this.contactRepository.findByNameContainingAndUser(query, user);
		return ResponseEntity.ok(contacts);   //data will be serialized from here automatically.
	}
}
