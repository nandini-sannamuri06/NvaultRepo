package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_invites database table.
 * 
 */
@Entity
@Table(name="user_invites")
@NamedQuery(name="UserInvite.findAll", query="SELECT u FROM UserInvite u")
public class UserInvite  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	private int expirytime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date invitedate;

	private String invitedby;

	//bi-directional many-to-one association to Convo
	@ManyToOne
	@JoinColumn(name="convoid")
	private Convo convo;

	//bi-directional many-to-one association to File
	@ManyToOne
	@JoinColumn(name="docid")
	private File file;

	public UserInvite() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getExpirytime() {
		return this.expirytime;
	}

	public void setExpirytime(int expirytime) {
		this.expirytime = expirytime;
	}

	public Date getInvitedate() {
		return this.invitedate;
	}

	public void setInvitedate(Date invitedate) {
		this.invitedate = invitedate;
	}

	public String getInvitedby() {
		return this.invitedby;
	}

	public void setInvitedby(String invitedby) {
		this.invitedby = invitedby;
	}

	public Convo getConvo() {
		return this.convo;
	}

	public void setConvo(Convo convo) {
		this.convo = convo;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}