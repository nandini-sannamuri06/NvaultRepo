package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the convos_content database table.
 * 
 */
@Entity
@Table(name="convos_content")
@NamedQuery(name="ConvosContent.findAll", query="SELECT c FROM ConvosContent c")
public class ConvosContent  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String content;

	private String created;

	private String filename;

	private String filename2;

	private String filename3;

	private String postedby;

	//bi-directional many-to-one association to Convo
	/*@ManyToOne
	@JoinColumn(name="navid")
	private Convo convo1;*/

	//bi-directional many-to-one association to Convo
	/*@ManyToOne
	@JoinColumn(name="navid")
	private Convo convo2;*/

	public ConvosContent() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreated() {
		return this.created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename2() {
		return this.filename2;
	}

	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}

	public String getFilename3() {
		return this.filename3;
	}

	public void setFilename3(String filename3) {
		this.filename3 = filename3;
	}

	public String getPostedby() {
		return this.postedby;
	}

	public void setPostedby(String postedby) {
		this.postedby = postedby;
	}

	/*public Convo getConvo1() {
		return this.convo1;
	}

	public void setConvo1(Convo convo1) {
		this.convo1 = convo1;
	}

	public Convo getConvo2() {
		return this.convo2;
	}

	public void setConvo2(Convo convo2) {
		this.convo2 = convo2;
	}*/

}