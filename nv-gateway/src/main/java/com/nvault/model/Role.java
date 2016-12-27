package com.nvault.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="role")
public class Role implements Serializable {
	
	@Id
	@Column(name="role_id")
	    private int id;

	    @NotNull
	    private String code;

	    @NotNull
	    private String label;

	    public Role() {

	    }


	    public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getLabel() {
	        return label;
	    }

	    public void setLabel(String label) {
	        this.label = label;
	    }

}
