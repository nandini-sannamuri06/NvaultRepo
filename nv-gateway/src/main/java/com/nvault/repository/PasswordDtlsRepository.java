package com.nvault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nvault.model.PasswordDetails;

@Repository
public interface PasswordDtlsRepository extends JpaRepository<PasswordDetails, Integer>{
	@Query("from PasswordDetails where unique_id=?1 and expired=1")
	PasswordDetails findByUniqueId(String uniqueId);

}
