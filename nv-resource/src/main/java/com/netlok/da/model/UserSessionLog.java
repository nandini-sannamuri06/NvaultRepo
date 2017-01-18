package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_session_log database table.
 * 
 */
@Entity
@Table(name="user_session_log")
@NamedQuery(name="UserSessionLog.findAll", query="SELECT u FROM UserSessionLog u")
public class UserSessionLog  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String action;

	private String answer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	private String ipaddress;

	private String sessionid;

	private String username;

	public UserSessionLog() {
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

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getSessionid() {
		return this.sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}