package com.nvault.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nvault.message.model.Message;
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
