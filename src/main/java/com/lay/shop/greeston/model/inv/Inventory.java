package com.lay.shop.greeston.model.inv;

import com.lay.shop.common.persistence.BaseModel;
/***
 * 
 * @author Lay
 * @date 2017年8月17日 上午11:32:31
 * @since
 */
public class Inventory extends BaseModel {
	
	
	/** */
    private static final long serialVersionUID = 4457739210492569983L;
    /** 品牌编码 */
	private String brandCode;
	/** 品牌名称 */
	private String brandName;
	/** 商品编码 */
	private String skuCode;
	/** 商品名称 */
	private String skuName;
	/** 当前库存数量 */
	private Integer quantity;
	/** 原始库存数量 */
	private Integer originalQuantity;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 修改时间 */
	private java.util.Date version;

	public Inventory(){
	}

	public void setBrandCode(String value) {
		this.brandCode = value;
	}
	
	public String getBrandCode() {
		return this.brandCode;
	}
	public void setBrandName(String value) {
		this.brandName = value;
	}
	
	public String getBrandName() {
		return this.brandName;
	}
	public void setSkuCode(String value) {
		this.skuCode = value;
	}
	
	public String getSkuCode() {
		return this.skuCode;
	}
	public void setSkuName(String value) {
		this.skuName = value;
	}
	
	public String getSkuName() {
		return this.skuName;
	}
	public void setQuantity(Integer value) {
		this.quantity = value;
	}
	
	public Integer getQuantity() {
		return this.quantity;
	}
	public void setOriginalQuantity(Integer value) {
		this.originalQuantity = value;
	}
	
	public Integer getOriginalQuantity() {
		return this.originalQuantity;
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
    
}

