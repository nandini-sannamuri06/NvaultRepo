package com.nvault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nvault.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
User findByUsername(String username);

}
