package com.contact.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contact.entities.Contact;
import com.contact.entities.User;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

	//pagination
	@Query("from Contact as c where c.user.id =:userId")
	public Page<Contact> findContactsByUser(@Param("userId") int userId, Pageable pageable);
	//Pageable carries two information ---> CurrentPage, Contacts Per page
	
	public List<Contact> findByNameContainingAndUser(String nameKeyword, User user);
	//predefine way of searching 
	//Here we can also use the like and by creating the pattern we can also search.
}
