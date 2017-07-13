package com.lay.shop.modules.sys.model;

import com.lay.shop.common.persistence.BaseModel;

/**
 * 
 * @author Lay
 * @date 2017年7月6日 下午6:48:49
 * @since
 */
public class Button extends BaseModel {
	
	/** */
    private static final long serialVersionUID = 8875824739568133516L;
    /** 按钮功能编码 */
	private String code;
	/** 按钮功能描述 */
	private String name;

	public Button(){
	}

	
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
}

