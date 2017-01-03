package com.nvault.doc.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nvault.model.NVaultUser;


@Entity
@Table(name = "document")
public class UserDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int archive;

	@Column(name="f_name")
	private String fileName;

	private String path;

	private int trash;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="userid")
	private NVaultUser user;

	public UserDoc() {
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

	public NVaultUser getUser() {
		return this.user;
	}

	public void setUser(NVaultUser user) {
		this.user = user;
	}

	
}
