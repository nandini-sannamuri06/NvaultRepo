package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_media database table.
 * 
 */
@Entity
@Table(name="user_media")
@NamedQuery(name="UserMedia.findAll", query="SELECT u FROM UserMedia u")
public class UserMedia  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	@Column(name="image_location")
	private String imageLocation;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userMedia")
	private List<User> users;

	public UserMedia() {
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

	public String getImageLocation() {
		return this.imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUserMedia(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUserMedia(null);

		return user;
	}

}