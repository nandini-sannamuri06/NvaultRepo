package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="role_description")
	private String roleDescription;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public UserRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}