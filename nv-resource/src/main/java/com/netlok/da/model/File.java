package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the files database table.
 * 
 */
@Entity
@Table(name="files")
@NamedQuery(name="File.findAll", query="SELECT f FROM File f")
public class File  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int archived;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateadded;

	private int deleted;

	private String description;

	private String email;

	private String filename;

	private int opened;

	private double size;

	//bi-directional one-to-one association to FileShare
	@OneToOne(mappedBy="file")
	private FileShare fileShare;

	//bi-directional many-to-one association to FilesContent
	@OneToMany(mappedBy="file")
	private List<FilesContent> filesContents;

	//bi-directional many-to-one association to UserInvite
	@OneToMany(mappedBy="file")
	private List<UserInvite> userInvites;

	public File() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArchived() {
		return this.archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	public Date getDateadded() {
		return this.dateadded;
	}

	public void setDateadded(Date dateadded) {
		this.dateadded = dateadded;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getOpened() {
		return this.opened;
	}

	public void setOpened(int opened) {
		this.opened = opened;
	}

	public double getSize() {
		return this.size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public FileShare getFileShare() {
		return this.fileShare;
	}

	public void setFileShare(FileShare fileShare) {
		this.fileShare = fileShare;
	}

	public List<FilesContent> getFilesContents() {
		return this.filesContents;
	}

	public void setFilesContents(List<FilesContent> filesContents) {
		this.filesContents = filesContents;
	}

	public FilesContent addFilesContent(FilesContent filesContent) {
		getFilesContents().add(filesContent);
		filesContent.setFile(this);

		return filesContent;
	}

	public FilesContent removeFilesContent(FilesContent filesContent) {
		getFilesContents().remove(filesContent);
		filesContent.setFile(null);

		return filesContent;
	}

	public List<UserInvite> getUserInvites() {
		return this.userInvites;
	}

	public void setUserInvites(List<UserInvite> userInvites) {
		this.userInvites = userInvites;
	}

	public UserInvite addUserInvite(UserInvite userInvite) {
		getUserInvites().add(userInvite);
		userInvite.setFile(this);

		return userInvite;
	}

	public UserInvite removeUserInvite(UserInvite userInvite) {
		getUserInvites().remove(userInvite);
		userInvite.setFile(null);

		return userInvite;
	}

}