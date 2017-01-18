package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the addressbook_convos database table.
 * 
 */
@Entity
@Table(name="addressbook_convos")
@NamedQuery(name="AddressbookConvo.findAll", query="SELECT a FROM AddressbookConvo a")
public class AddressbookConvo  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int addressid;

	private String convoid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateadded;

	//bi-directional one-to-one association to Addressbook
	@OneToOne
	@JoinColumn(name="id")
	private Addressbook addressbook;

	//bi-directional one-to-one association to Convo
	@OneToOne
	@JoinColumn(name="id")
	private Convo convo;

	public AddressbookConvo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAddressid() {
		return this.addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}

	public String getConvoid() {
		return this.convoid;
	}

	public void setConvoid(String convoid) {
		this.convoid = convoid;
	}

	public Date getDateadded() {
		return this.dateadded;
	}

	public void setDateadded(Date dateadded) {
		this.dateadded = dateadded;
	}

	public Addressbook getAddressbook() {
		return this.addressbook;
	}

	public void setAddressbook(Addressbook addressbook) {
		this.addressbook = addressbook;
	}

	public Convo getConvo() {
		return this.convo;
	}

	public void setConvo(Convo convo) {
		this.convo = convo;
	}

}