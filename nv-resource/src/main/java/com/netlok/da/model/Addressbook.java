package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the addressbook database table.
 * 
 */
@Entity
@NamedQuery(name="Addressbook.findAll", query="SELECT a FROM Addressbook a")
public class Addressbook  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	private String cell;

	private String city;

	private String email;

	private String firstname;

	private String lastname;

	private String phone;

	private String state;

	@Column(name="user_id")
	private int userId;

	private String zip;

	//bi-directional one-to-one association to AddressbookConvo
	@OneToOne(mappedBy="addressbook")
	private AddressbookConvo addressbookConvo;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="addressbook")
	private List<User> users;

	public Addressbook() {
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

	public String getCell() {
		return this.cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public AddressbookConvo getAddressbookConvo() {
		return this.addressbookConvo;
	}

	public void setAddressbookConvo(AddressbookConvo addressbookConvo) {
		this.addressbookConvo = addressbookConvo;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setAddressbook(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setAddressbook(null);

		return user;
	}

}