package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the convos database table.
 * 
 */
@Entity
@Table(name="convos")
@NamedQuery(name="Convo.findAll", query="SELECT c FROM Convo c")
public class Convo  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int archived;

	private int deleted;

	private int expiration;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationstart;

	private int hidden;

	private String name;

	private int opened;

	//bi-directional one-to-one association to AddressbookConvo
	@OneToOne(mappedBy="convo")
	private AddressbookConvo addressbookConvo;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	/*//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user2;*/

	/*//bi-directional many-to-one association to ConvosContent
	@OneToMany(mappedBy="convo1")
	private List<ConvosContent> convosContents1;

	//bi-directional many-to-one association to ConvosContent
	@OneToMany(mappedBy="convo2")
	private List<ConvosContent> convosContents2;*/

	//bi-directional many-to-one association to UserInvite
	@OneToMany(mappedBy="convo")
	private List<UserInvite> userInvites;

	public Convo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArchived() {
		return this.archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getExpiration() {
		return this.expiration;
	}

	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}

	public Date getExpirationstart() {
		return this.expirationstart;
	}

	public void setExpirationstart(Date expirationstart) {
		this.expirationstart = expirationstart;
	}

	public int getHidden() {
		return this.hidden;
	}

	public void setHidden(int hidden) {
		this.hidden = hidden;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOpened() {
		return this.opened;
	}

	public void setOpened(int opened) {
		this.opened = opened;
	}

	public AddressbookConvo getAddressbookConvo() {
		return this.addressbookConvo;
	}

	public void setAddressbookConvo(AddressbookConvo addressbookConvo) {
		this.addressbookConvo = addressbookConvo;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}*/

	/*public List<ConvosContent> getConvosContents1() {
		return this.convosContents1;
	}

	public void setConvosContents1(List<ConvosContent> convosContents1) {
		this.convosContents1 = convosContents1;
	}*/

	/*public ConvosContent addConvosContents1(ConvosContent convosContents1) {
		getConvosContents1().add(convosContents1);
		convosContents1.setConvo1(this);

		return convosContents1;
	}

	public ConvosContent removeConvosContents1(ConvosContent convosContents1) {
		getConvosContents1().remove(convosContents1);
		convosContents1.setConvo1(null);

		return convosContents1;
	}*/

	/*public List<ConvosContent> getConvosContents2() {
		return this.convosContents2;
	}

	public void setConvosContents2(List<ConvosContent> convosContents2) {
		this.convosContents2 = convosContents2;
	}*/

	/*public ConvosContent addConvosContents2(ConvosContent convosContents2) {
		getConvosContents2().add(convosContents2);
		convosContents2.setConvo2(this);

		return convosContents2;
	}

	public ConvosContent removeConvosContents2(ConvosContent convosContents2) {
		getConvosContents2().remove(convosContents2);
		convosContents2.setConvo2(null);

		return convosContents2;
	}*/

	public List<UserInvite> getUserInvites() {
		return this.userInvites;
	}

	public void setUserInvites(List<UserInvite> userInvites) {
		this.userInvites = userInvites;
	}

	public UserInvite addUserInvite(UserInvite userInvite) {
		getUserInvites().add(userInvite);
		userInvite.setConvo(this);

		return userInvite;
	}

	public UserInvite removeUserInvite(UserInvite userInvite) {
		getUserInvites().remove(userInvite);
		userInvite.setConvo(null);

		return userInvite;
	}

}