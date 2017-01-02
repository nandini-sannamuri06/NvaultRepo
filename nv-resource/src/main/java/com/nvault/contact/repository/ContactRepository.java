package com.nvault.contact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nvault.contact.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	@Query("FROM Contact c WHERE c.userId = :userId")
	List<Contact> findAll(@Param("userId")String userId);

}
