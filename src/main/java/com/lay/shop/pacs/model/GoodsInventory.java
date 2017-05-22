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
 package com.lay.shop.pacs.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * 
 * @author 江家雷
 * @date 2017年5月22日 下午5:02:04
 * @since
 */
public class GoodsInventory extends BaseModel {
	
	/** */
    private static final long serialVersionUID = 7952746749806095880L;
    
	public static final String TABLE_ALIAS = "GoodsInventory";
	public static final String ALIAS_COMPANY_CODE = "公司编码";
	public static final String ALIAS_SKU_CODE = "商品编码";
	public static final String ALIAS_QUANTITY = "数量";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_VERSION = "修改时间";
	
	/** 公司编码 */
	private String companyCode;
	/** 商品编码 */
	private String skuCode;
	/** 数量 */
	private Integer quantity;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 修改时间 */
	private java.util.Date version;

	public GoodsInventory(){
	}

	public GoodsInventory(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setCompanyCode(String value) {
		this.companyCode = value;
	}
	
	public String getCompanyCode() {
		return this.companyCode;
	}
	public void setSkuCode(String value) {
		this.skuCode = value;
	}
	
	public String getSkuCode() {
		return this.skuCode;
	}
	public void setQuantity(Integer value) {
		this.quantity = value;
	}
	
	public Integer getQuantity() {
		return this.quantity;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setVersion(java.util.Date value) {
		this.version = value;
	}
	
	public java.util.Date getVersion() {
		return this.version;
	}
    @Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("Id",getId())		
		.append("CompanyCode",getCompanyCode())		
		.append("SkuCode",getSkuCode())		
		.append("Quantity",getQuantity())		
		.append("CreateTime",getCreateTime())		
		.append("Version",getVersion())		
			.toString();
	}
    @Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(getId())
		.append(getCompanyCode())
		.append(getSkuCode())
		.append(getQuantity())
		.append(getCreateTime())
		.append(getVersion())
			.toHashCode();
	}
    @Override
	public boolean equals(Object obj) {
		if(obj instanceof GoodsInventory == false) return false;
		if(this == obj) return true;
		GoodsInventory other = (GoodsInventory)obj;
		return new EqualsBuilder()
		.append(getId(),other.getId())

		.append(getCompanyCode(),other.getCompanyCode())

		.append(getSkuCode(),other.getSkuCode())

		.append(getQuantity(),other.getQuantity())

		.append(getCreateTime(),other.getCreateTime())

		.append(getVersion(),other.getVersion())

			.isEquals();
	}
}

