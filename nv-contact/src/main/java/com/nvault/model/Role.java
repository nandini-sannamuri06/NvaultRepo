package com.nvault.model;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class Role implements Serializable {
	
	    private int id;

	    private String code;

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
