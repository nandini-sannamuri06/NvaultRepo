package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lastused_date")
	private Date lastusedDate;

	private String password;

	private int suspended;

	@Column(name="user_convoid")
	private int userConvoid;

	@Column(name="user_fileid")
	private int userFileid;

	@Column(name="user_groupid")
	private int userGroupid;

	private String username;

	//bi-directional many-to-one association to Convo
	@OneToMany(mappedBy="user")
	private List<Convo> convos;

	/*//bi-directional many-to-one association to Convo
	@OneToMany(mappedBy="user2")
	private List<Convo> convos2;*/

	//bi-directional many-to-one association to Addressbook
	@ManyToOne
	@JoinColumn(name="user_addressid")
	private Addressbook addressbook;

	//bi-directional many-to-one association to UserMedia
	@ManyToOne
	@JoinColumn(name="user_mediaid")
	private UserMedia userMedia;

	//bi-directional many-to-one association to UserPlan
	@ManyToOne
	@JoinColumn(name="user_planid")
	private UserPlan userPlan;

	//bi-directional many-to-one association to UserProfile
	@ManyToOne
	@JoinColumn(name="user_profileid")
	private UserProfile userProfile;

	//bi-directional many-to-one association to UserGroup
	@OneToMany(mappedBy="user")
	private List<UserGroup> userGroups;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getLastusedDate() {
		return this.lastusedDate;
	}

	public void setLastusedDate(Date lastusedDate) {
		this.lastusedDate = lastusedDate;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSuspended() {
		return this.suspended;
	}

	public void setSuspended(int suspended) {
		this.suspended = suspended;
	}

	public int getUserConvoid() {
		return this.userConvoid;
	}

	public void setUserConvoid(int userConvoid) {
		this.userConvoid = userConvoid;
	}

	public int getUserFileid() {
		return this.userFileid;
	}

	public void setUserFileid(int userFileid) {
		this.userFileid = userFileid;
	}

	public int getUserGroupid() {
		return this.userGroupid;
	}

	public void setUserGroupid(int userGroupid) {
		this.userGroupid = userGroupid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Convo> getConvos() {
		return this.convos;
	}

	public void setConvos(List<Convo> convos) {
		this.convos = convos;
	}

	/*public Convo addConvos1(Convo convos1) {
		getConvos1().add(convos1);
		convos1.setUser1(this);

		return convos1;
	}

	public Convo removeConvos1(Convo convos1) {
		getConvos1().remove(convos1);
		convos1.setUser1(null);

		return convos1;
	}*/

	/*public List<Convo> getConvos2() {
		return this.convos2;
	}

	public void setConvos2(List<Convo> convos2) {
		this.convos2 = convos2;
	}

	public Convo addConvos2(Convo convos2) {
		getConvos2().add(convos2);
		convos2.setUser2(this);

		return convos2;
	}

	public Convo removeConvos2(Convo convos2) {
		getConvos2().remove(convos2);
		convos2.setUser2(null);

		return convos2;
	}*/

	public Addressbook getAddressbook() {
		return this.addressbook;
	}

	public void setAddressbook(Addressbook addressbook) {
		this.addressbook = addressbook;
	}

	public UserMedia getUserMedia() {
		return this.userMedia;
	}

	public void setUserMedia(UserMedia userMedia) {
		this.userMedia = userMedia;
	}

	public UserPlan getUserPlan() {
		return this.userPlan;
	}

	public void setUserPlan(UserPlan userPlan) {
		this.userPlan = userPlan;
	}

	public UserProfile getUserProfile() {
		return this.userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<UserGroup> getUserGroups() {
		return this.userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public UserGroup addUserGroup(UserGroup userGroup) {
		getUserGroups().add(userGroup);
		userGroup.setUser(this);

		return userGroup;
	}

	public UserGroup removeUserGroup(UserGroup userGroup) {
		getUserGroups().remove(userGroup);
		userGroup.setUser(null);

		return userGroup;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

}