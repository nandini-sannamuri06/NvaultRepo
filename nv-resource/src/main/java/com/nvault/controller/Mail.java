package com.nvault.controller;

import java.util.List;

public class Mail {

	List<EmailToAddress> toAddress;
	
	//List<String> toAddress;
	String subject;
	String body;

	public Mail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mail(List<EmailToAddress> toAddress, String subject, String body) {
		super();
		this.toAddress = toAddress;
		this.subject = subject;
		this.body = body;
	}

	public List<EmailToAddress> getToAddress() {
		return toAddress;
	}

	public void setToAddress(List<EmailToAddress> toAddress) {
		this.toAddress = toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Mail [toAddress=" + toAddress + ", subject=" + subject + ", body=" + body + "]";
	}

}
