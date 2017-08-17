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
 package com.lay.shop.greeston.model.inv;

import com.lay.shop.common.persistence.BaseModel;
/***
 * 
 * @author Lay
 * @date 2017年8月17日 上午11:33:42
 * @since
 */
public class InventoryChange extends BaseModel {
	
	/** */
    private static final long serialVersionUID = 7021832572644856744L;
    
	/** 品牌编码 */
	private String brandCode;
	/** 品牌编码 */
	private String shopCode;
	/** 商品编码 */
	private String skuCode;
	/** 销售数量 */
	private Integer quantity;
	/** 库存变化表   0:未处理  1:已处理 2:已失败 */
	private Integer status;
	/** 库存变化表   0:未处理  1:已处理 2:已失败 */
	private String remark;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 更新时间 */
	private java.util.Date updateTime;
	/** 操作用户ID */
	private Long userId;


	public InventoryChange(){
	}

	public void setBrandCode(String value) {
		this.brandCode = value;
	}
	
	public String getBrandCode() {
		return this.brandCode;
	}
	public void setShopCode(String value) {
		this.shopCode = value;
	}
	
	public String getShopCode() {
		return this.shopCode;
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
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public Long getUserId() {
		return this.userId;
	}
   
}

