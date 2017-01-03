package com.nvault.doc.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nvault.doc.model.UserDoc;
@Repository
public interface FileRepository extends JpaRepository<UserDoc, Integer>{
	//List<UserFile> findAll(String userId);
	@SuppressWarnings("unchecked")
	UserDoc save(UserDoc userFile);
	
	@Query("from UserDoc u  where u.archive=1")
	List<UserDoc> findByArchieved();

	@Query("from UserDoc u  where u.trash=1")
	List<UserDoc> findByTrash();

}
