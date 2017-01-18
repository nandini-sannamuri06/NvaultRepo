package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_family database table.
 * 
 */
@Entity
@Table(name="user_family")
@NamedQuery(name="UserFamily.findAll", query="SELECT u FROM UserFamily u")
public class UserFamily  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String createdby;

	public UserFamily() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

}