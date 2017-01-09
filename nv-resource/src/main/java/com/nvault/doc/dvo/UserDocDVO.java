package com.nvault.doc.dvo;

import java.io.Serializable;
import java.util.Date;


public class UserDocDVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int archive;

	private String fileName;

	private String path;

	private int trash;

	private int userId;
	
	private long size;
	
	private Date modifiedDate;
	
	private String fileType;
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "UserDocDVO [id=" + id + ", archive=" + archive + ", fileName=" + fileName + ", path=" + path
				+ ", trash=" + trash + ", userId=" + userId + ", size=" + size + ", modifiedDate=" + modifiedDate
				+ ", fileType=" + fileType + "]";
	}

	


	
}
