package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the files_content database table.
 * 
 */
@Entity
@Table(name="files_content")
@NamedQuery(name="FilesContent.findAll", query="SELECT f FROM FilesContent f")
public class FilesContent  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int converted;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateadded;

	@Lob
	private String description;

	private String filekey;

	private String s3filename;

	@Column(name="user_num")
	private int userNum;

	//bi-directional many-to-one association to File
	@ManyToOne
	@JoinColumn(name="fileid")
	private File file;

	public FilesContent() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConverted() {
		return this.converted;
	}

	public void setConverted(int converted) {
		this.converted = converted;
	}

	public Date getDateadded() {
		return this.dateadded;
	}

	public void setDateadded(Date dateadded) {
		this.dateadded = dateadded;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilekey() {
		return this.filekey;
	}

	public void setFilekey(String filekey) {
		this.filekey = filekey;
	}

	public String getS3filename() {
		return this.s3filename;
	}

	public void setS3filename(String s3filename) {
		this.s3filename = s3filename;
	}

	public int getUserNum() {
		return this.userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}