package com.nvault.message.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "subject")
	private String subject;

	@Column(name = "recipient")
	private String recipient;

	@Column(name = "body")
	private String body;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@Column(name = "sender")
	private String sender;

	@Column(name = "trash")
	private int trash;

	@Column(name = "archieved")
	private int archieved;
	
	@Column(name = "user_id")
	private Integer user_id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private EMailSendStatus emailSendStatus;
	
	

	public EMailSendStatus getEmailSendStatus() {
		return emailSendStatus;
	}

	public void setEmailSendStatus(EMailSendStatus emailSendStatus) {
		this.emailSendStatus = emailSendStatus;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTrash() {
		return trash;
	}

	public void setTrash(int trash) {
		this.trash = trash;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getArchieved() {
		return archieved;
	}

	public void setArchieved(int archieved) {
		this.archieved = archieved;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Message(int id, String subject, String recipient, String body, Date date, String sender, int trash,
			int archieved, int user_id) {
		super();
		this.id = id;
		this.subject = subject;
		this.recipient = recipient;
		this.body = body;
		this.date = date;
		this.sender = sender;
		this.trash = trash;
		this.archieved = archieved;
		this.user_id=user_id;
	}

	

	@Override
	public String toString() {
		return "Message [id=" + id + ", subject=" + subject + ", recipient=" + recipient + ", body=" + body + ", date="
				+ date + ", sender=" + sender + ", trash=" + trash + ", archieved=" + archieved + ", user_id=" + user_id
				+ "]";
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

}
