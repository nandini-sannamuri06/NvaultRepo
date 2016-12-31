package com.nvault.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="passwordDtls")
public class PasswordDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	public String uniqueId;
	
	public int expired;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getExpired() {
		return expired;
	}

	public void setExpired(int expired) {
		this.expired = expired;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PasswordDetails [id=" + id + ", uniqueId=" + uniqueId + ", expired=" + expired + "]";
	}
	
	

}
