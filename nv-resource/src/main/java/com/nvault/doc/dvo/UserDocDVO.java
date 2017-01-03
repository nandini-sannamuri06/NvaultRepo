package com.nvault.doc.dvo;

import java.io.Serializable;


public class UserDocDVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int archive;

	private String fileName;

	private String path;

	private int trash;

	private int userId;

	public UserDocDVO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArchive() {
		return this.archive;
	}

	public void setArchive(int archive) {
		this.archive = archive;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getTrash() {
		return this.trash;
	}

	public void setTrash(int trash) {
		this.trash = trash;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	
}
