package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_activity_log database table.
 * 
 */
@Entity
@Table(name="user_activity_log")
@NamedQuery(name="UserActivityLog.findAll", query="SELECT u FROM UserActivityLog u")
public class UserActivityLog  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String action;

	@Column(name="user_email")
	private String userEmail;

	public UserActivityLog() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}