package com.lay.shop.pacs.orm.model;

import java.io.Serializable;

public class BaseModel implements Serializable {

	private static final long serialVersionUID = -5253902746677991352L;
	
	protected Long id;
	
	public BaseModel(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}