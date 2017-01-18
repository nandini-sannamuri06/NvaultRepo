package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user_profile database table.
 * 
 */
@Entity
@Table(name="user_profile")
@NamedQuery(name="UserProfile.findAll", query="SELECT u FROM UserProfile u")
public class UserProfile  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	private String city;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private String email;

	@Lob
	private byte[] image1;

	@Lob
	private byte[] image2;

	@Lob
	private byte[] image3;

	private String state;

	private byte suspended;

	@Column(name="user_firstname")
	private String userFirstname;

	@Column(name="user_lastname")
	private String userLastname;

	@Column(name="user_name")
	private String userName;

	private int zip;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userProfile")
	private List<User> users;

	public UserProfile() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getImage1() {
		return this.image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return this.image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public byte[] getImage3() {
		return this.image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public byte getSuspended() {
		return this.suspended;
	}

	public void setSuspended(byte suspended) {
		this.suspended = suspended;
	}

	public String getUserFirstname() {
		return this.userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserLastname() {
		return this.userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getZip() {
		return this.zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUserProfile(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUserProfile(null);

		return user;
	}

}