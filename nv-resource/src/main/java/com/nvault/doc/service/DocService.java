package com.nvault.doc.service;

import org.springframework.stereotype.Service;

import com.nvault.doc.model.UserDoc;
import com.nvault.doc.repository.FileRepository;
@Service
public class DocService {
	FileRepository fileRepository;
	
	/*public List<UserFile> getAllFiles(String userId){
		return fileRepository.findAll(userId);
	}*/
	
	public void saveFile(UserDoc userFile){
		fileRepository.save(userFile);
	}
}
