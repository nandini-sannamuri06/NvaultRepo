package com.nvault.message.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nvault.message.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("from Message u  where u.archieved=1")
	List<Message> findByArchieved();

	@Query("from Message u  where u.trash=1")
	List<Message> findByTrash();

	@Query("from Message u  where u.archieved!=1 and u.trash!=1")
	List<Message> getAllMessages();

	@Query("from Message u  where  u.trash!=1")
	List<Message> getMessagesInclArchie();

}
