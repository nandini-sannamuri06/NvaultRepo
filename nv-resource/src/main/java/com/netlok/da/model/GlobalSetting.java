package com.netlok.da.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the global_settings database table.
 * 
 */
@Entity
@Table(name="global_settings")
@NamedQuery(name="GlobalSetting.findAll", query="SELECT g FROM GlobalSetting g")
public class GlobalSetting  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String s3accesskey;

	private String s3awsfilefolder;

	private String s3awspath;

	private String s3filefolder;

	private String s3permid;

	private String s3secretkey;

	private String twiliophone;

	private String twiliopwd;

	private String twiliouser;

	public GlobalSetting() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getS3accesskey() {
		return this.s3accesskey;
	}

	public void setS3accesskey(String s3accesskey) {
		this.s3accesskey = s3accesskey;
	}

	public String getS3awsfilefolder() {
		return this.s3awsfilefolder;
	}

	public void setS3awsfilefolder(String s3awsfilefolder) {
		this.s3awsfilefolder = s3awsfilefolder;
	}

	public String getS3awspath() {
		return this.s3awspath;
	}

	public void setS3awspath(String s3awspath) {
		this.s3awspath = s3awspath;
	}

	public String getS3filefolder() {
		return this.s3filefolder;
	}

	public void setS3filefolder(String s3filefolder) {
		this.s3filefolder = s3filefolder;
	}

	public String getS3permid() {
		return this.s3permid;
	}

	public void setS3permid(String s3permid) {
		this.s3permid = s3permid;
	}

	public String getS3secretkey() {
		return this.s3secretkey;
	}

	public void setS3secretkey(String s3secretkey) {
		this.s3secretkey = s3secretkey;
	}

	public String getTwiliophone() {
		return this.twiliophone;
	}

	public void setTwiliophone(String twiliophone) {
		this.twiliophone = twiliophone;
	}

	public String getTwiliopwd() {
		return this.twiliopwd;
	}

	public void setTwiliopwd(String twiliopwd) {
		this.twiliopwd = twiliopwd;
	}

	public String getTwiliouser() {
		return this.twiliouser;
	}

	public void setTwiliouser(String twiliouser) {
		this.twiliouser = twiliouser;
	}

}