package com.nvault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nvault.model.NVaultUser;

@Repository
public interface UserRepository extends JpaRepository<NVaultUser, Integer> {
NVaultUser findByUsername(String username);

}
