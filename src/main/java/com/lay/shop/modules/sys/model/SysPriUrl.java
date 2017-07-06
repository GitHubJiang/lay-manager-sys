/**
 * Copyright (c) 2013 Baozun All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Baozun.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Baozun.
 *
 * BAOZUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. BAOZUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
 package com.lay.shop.modules.sys.model;

import com.lay.shop.common.persistence.BaseModel;
/**
 * 
 * @author larkark
 *
 */
public class SysPriUrl extends BaseModel {
	
	/** */
    private static final long serialVersionUID = 6507795546855685436L;
    /** 权限ACL */
	private String acl;
	/** URL的ID */
	private Long urlId;

	public SysPriUrl(){
	}

	public void setAcl(String value) {
		this.acl = value;
	}
	
	public String getAcl() {
		return this.acl;
	}
	public void setUrlId(Long value) {
		this.urlId = value;
	}
	
	public Long getUrlId() {
		return this.urlId;
	}
    
}

