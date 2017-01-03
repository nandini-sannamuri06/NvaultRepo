package com.nvault.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nvault.model.NVaultUser;

@Repository
public interface UserRepository extends JpaRepository<NVaultUser, Integer> {
	NVaultUser findByUsername(String username);

    @Query("select u from NVaultUser u where u.mail=:mail")
	NVaultUser findByMail(@Param("mail")String mail);

//    @Modifying(clearAutomatically = true)
//    @Transactional(rollbackOn =Exception.class)
//	@Query("update NVaultUser u set u.password=?1 where u.mail=?2 and u.username=?3")
//	Integer setPassword(String password, String mail,String username) throws Exception;

}
