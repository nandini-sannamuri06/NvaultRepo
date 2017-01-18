package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the addressbook_files database table.
 * 
 */
@Entity
@Table(name="addressbook_files")
@NamedQuery(name="AddressbookFile.findAll", query="SELECT a FROM AddressbookFile a")
public class AddressbookFile  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int addressid;

	@Temporal(TemporalType.DATE)
	private Date dateadded;

	private int docid;

	public AddressbookFile() {
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

	public Date getDateadded() {
		return this.dateadded;
	}

	public void setDateadded(Date dateadded) {
		this.dateadded = dateadded;
	}

	public int getDocid() {
		return this.docid;
	}

	public void setDocid(int docid) {
		this.docid = docid;
	}

}