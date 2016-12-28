package com.nvault.controller.email;

import java.util.Arrays;

public class UserEmail {

	
	private String[] toAddress;
	private String body;
	private String subject;
	private String[] recipient;
	public String[] getToAddress() {
		return toAddress;
	}
	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String[] getRecipient() {
		return recipient;
	}
	public void setRecipient(String[] recipient) {
		this.recipient = recipient;
	}
	@Override
	public String toString() {
		return "UserEmail [toAddress=" + Arrays.toString(toAddress) + ", body=" + body + ", subject=" + subject
				+ ", recipient=" + Arrays.toString(recipient) + "]";
	}
	
	
	
	
	
}
