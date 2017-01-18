package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_plan database table.
 * 
 */
@Entity
@Table(name="user_plan")
@NamedQuery(name="UserPlan.findAll", query="SELECT u FROM UserPlan u")
public class UserPlan  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String active;

	private String type;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userPlan")
	private List<User> users;

	public UserPlan() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUserPlan(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUserPlan(null);

		return user;
	}

}