package com.lay.shop.pacs.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * 
 * @author larkark
 *
 */
public class ItemSku extends BaseModel {
    
    /** */
    private static final long serialVersionUID = 427221189300848228L;
    
    /** 公司编码 */
    private String companyCode;
    /** SKU编码 */
    private String skuCode;
    /** 货号 */
    private String supplierSkuCode;
    /** 条形码 */
    private String barCode;
    /** 商品名称 */
    private String skuName;
    /** 商品属性 */
    private String skuProperties;
    /** 商品图片URL */
    private String imgUrl;
    /** 创建时间 */
    private java.util.Date createTime;
    /** 修改时间 */
    private java.util.Date updateTime;
    /** 备注信息 */
    private String remark;

    public ItemSku(){
    }

    public ItemSku(
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
    public void setSupplierSkuCode(String value) {
        this.supplierSkuCode = value;
    }
    
    public String getSupplierSkuCode() {
        return this.supplierSkuCode;
    }
    public void setBarCode(String value) {
        this.barCode = value;
    }
    
    public String getBarCode() {
        return this.barCode;
    }
    public void setSkuName(String value) {
        this.skuName = value;
    }
    
    public String getSkuName() {
        return this.skuName;
    }
    public void setSkuProperties(String value) {
        this.skuProperties = value;
    }
    
    public String getSkuProperties() {
        return this.skuProperties;
    }
    public void setImgUrl(String value) {
        this.imgUrl = value;
    }
    
    public String getImgUrl() {
        return this.imgUrl;
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
    public void setRemark(String value) {
        this.remark = value;
    }
    
    public String getRemark() {
        return this.remark;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
        .append("Id",getId())       
        .append("CompanyCode",getCompanyCode())     
        .append("SkuCode",getSkuCode())     
        .append("SupplierSkuCode",getSupplierSkuCode())     
        .append("BarCode",getBarCode())     
        .append("SkuName",getSkuName())     
        .append("SkuProperties",getSkuProperties())     
        .append("ImgUrl",getImgUrl())       
        .append("CreateTime",getCreateTime())       
        .append("UpdateTime",getUpdateTime())       
        .append("Remark",getRemark())       
            .toString();
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append(getId())
        .append(getCompanyCode())
        .append(getSkuCode())
        .append(getSupplierSkuCode())
        .append(getBarCode())
        .append(getSkuName())
        .append(getSkuProperties())
        .append(getImgUrl())
        .append(getCreateTime())
        .append(getUpdateTime())
        .append(getRemark())
            .toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ItemSku == false) return false;
        if(this == obj) return true;
        ItemSku other = (ItemSku)obj;
        return new EqualsBuilder()
        .append(getId(),other.getId())

        .append(getCompanyCode(),other.getCompanyCode())

        .append(getSkuCode(),other.getSkuCode())

        .append(getSupplierSkuCode(),other.getSupplierSkuCode())

        .append(getBarCode(),other.getBarCode())

        .append(getSkuName(),other.getSkuName())

        .append(getSkuProperties(),other.getSkuProperties())

        .append(getImgUrl(),other.getImgUrl())

        .append(getCreateTime(),other.getCreateTime())

        .append(getUpdateTime(),other.getUpdateTime())

        .append(getRemark(),other.getRemark())

            .isEquals();
    }
}

