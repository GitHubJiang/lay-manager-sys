package com.lay.shop.greeston.model.auth;

import com.lay.shop.common.persistence.BaseModel;

/**
 * URL
 * @author Lay
 * @date 2017年8月2日 下午3:07:10
 * @since
 */
public class Url extends BaseModel {
	
	/** */
    private static final long serialVersionUID = -4508297754751057146L;
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

