package com.nvault.doc.service;

import java.util.List;

import com.nvault.doc.model.UserDoc;

public interface DocumentService {

	UserDoc deleteDoc(Integer id);

	UserDoc archieveDoc(Integer id);

	List<UserDoc> getArchiveDocs();

	List<UserDoc> getTrashDocs();

}
