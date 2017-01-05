package com.nvault.doc.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvault.contact.model.Contact;
import com.nvault.doc.model.UserDoc;
import com.nvault.doc.repository.FileRepository;
import com.nvault.doc.service.DocumentService;
import com.nvault.message.model.Message;
import com.nvault.message.repository.MessageRepository;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	FileRepository fileRepository;

	public UserDoc deleteDoc(Integer id) {
		UserDoc userDoc = fileRepository.findOne(id);
		if(userDoc!=null){
			userDoc.setTrash(1);
			userDoc = fileRepository.save(userDoc);
		}
		return userDoc;
	}

	@Override
	public UserDoc archieveDoc(Integer id) {
		UserDoc userDoc = fileRepository.findOne(id);
		if(userDoc!=null){
			userDoc.setArchive(1);
			userDoc = fileRepository.save(userDoc);
		}
		return userDoc;
	}

	@Override
	public List<UserDoc> getArchiveDocs() {

		return fileRepository.findByArchieved();
	}

	@Override
	public List<UserDoc> getTrashDocs() {
		return fileRepository.findByTrash();
	}

	@Override
	public UserDoc getDoc(Integer id) {
		return fileRepository.findOne(id);
	}

	@Override
	public List<UserDoc> getAllDocs() {
		return fileRepository.findAllDocs();
	}

}
