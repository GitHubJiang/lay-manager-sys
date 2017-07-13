package com.lay.shop.modules.sys.model;


import com.lay.shop.common.persistence.BaseModel;

/**
 * 
 * @author larkark
 *
 */
public class Url extends BaseModel {

	/** */
    private static final long serialVersionUID = -3681368686652395736L;
    /** 受管控URL */
	private String url;
	
	public Url(){
	}

	public Url(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setUrl(String value) {
		this.url = value;
	}
	
	public String getUrl() {
		return this.url;
	}
    
}

