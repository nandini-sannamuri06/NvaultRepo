package com.nvault.doc.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nvault.doc.model.UserDoc;
@Repository
public interface FileRepository extends CrudRepository<UserDoc, String>{
	//List<UserFile> findAll(String userId);
	@SuppressWarnings("unchecked")
	UserDoc save(UserDoc userFile);
}
