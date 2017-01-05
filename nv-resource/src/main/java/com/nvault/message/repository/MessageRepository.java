package com.nvault.message.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nvault.message.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("from Message u  where u.archieved=1 and u.user_id = :userId")
	List<Message> findByArchieved(@Param("userId")Integer userId);

	@Query("from Message u  where u.trash=1 and u.user_id = :userId")
	List<Message> findByTrash(@Param("userId")Integer userId);

	@Query("from Message u  where u.archieved!=1 and u.trash!=1 and u.user_id = :userId")
	List<Message> getAllMessages(@Param("userId")Integer userId);

	@Query("from Message u  where  u.trash!=1 and u.user_id = :userId")
	List<Message> getMessagesInclArchie(@Param("userId")Integer userId);

}
