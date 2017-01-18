package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the file_shares database table.
 * 
 */
@Entity
@Table(name="file_shares")
@NamedQuery(name="FileShare.findAll", query="SELECT f FROM FileShare f")
public class FileShare  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date accepted;

	private int archived;

	private int deleted;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteddate;

	private String email;

	private int fileid;

	private int readonly;

	@Temporal(TemporalType.TIMESTAMP)
	private Date shared;

	private int suspended;

	//bi-directional one-to-one association to File
	@OneToOne
	@JoinColumn(name="id")
	private File file;

	public FileShare() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAccepted() {
		return this.accepted;
	}

	public void setAccepted(Date accepted) {
		this.accepted = accepted;
	}

	public int getArchived() {
		return this.archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Date getDeleteddate() {
		return this.deleteddate;
	}

	public void setDeleteddate(Date deleteddate) {
		this.deleteddate = deleteddate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFileid() {
		return this.fileid;
	}

	public void setFileid(int fileid) {
		this.fileid = fileid;
	}

	public int getReadonly() {
		return this.readonly;
	}

	public void setReadonly(int readonly) {
		this.readonly = readonly;
	}

	public Date getShared() {
		return this.shared;
	}

	public void setShared(Date shared) {
		this.shared = shared;
	}

	public int getSuspended() {
		return this.suspended;
	}

	public void setSuspended(int suspended) {
		this.suspended = suspended;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}