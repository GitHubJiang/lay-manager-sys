package com.lay.shop.modules.sys.command;

import com.alibaba.fastjson.annotation.JSONField;
import com.lay.shop.common.persistence.TreeCommand;

public class MenuCommand extends TreeCommand{
   
    /** 所有父级编号 */
    private String parentIds;
    /** 链接 */
    private String href;
    /** 图标 */
    private String icon;
    /** 1.主菜单  2.子菜单 */
    private Integer type;
    /** 备注信息 */
    private String remarks;
    /** 1.正常；2.已删除  */
    private Integer lifecycle;
    
    private Boolean spread  =true;
    
    @JSONField(name="pid")
    public String getParentIds() {
        return parentIds;
    }
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }
    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public Integer getLifecycle() {
        return lifecycle;
    }
    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }
    public Boolean getSpread() {
        return spread;
    }
    public void setSpread(Boolean spread) {
        this.spread = spread;
    }    

    
}
