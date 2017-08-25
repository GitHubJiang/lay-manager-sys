package com.lay.shop.greeston.command.auth;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lay.shop.greeston.serializer.OpUnitTreeSerializer;
@JsonSerialize(using =OpUnitTreeSerializer.class)
public class OpUnitTreeCommand implements Serializable {

	/** */
    private static final long serialVersionUID = -8428299518851895382L;

    private Long id;
    
	/** 组织编码 */
    private String code;

    /** 组织名称 */
    private String name;
    
    private String selectable;
    
    /** 组织类型 */
    private Long ouTypeId;
    
    /** 组织类型名称 */
    private String ouTypeName;
    /**1.可用;2.已禁用(无效);3.已删除*/
    private Integer lifecycle;

    /** 父组织 */
    private Long parentUnitId;
    
    private List<OpUnitTreeCommand> nodes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelectable() {
		return selectable;
	}

	public void setSelectable(String selectable) {
		this.selectable = selectable;
	}

	public List<OpUnitTreeCommand> getNodes() {
		return nodes;
	}

	public void setNodes(List<OpUnitTreeCommand> nodes) {
		this.nodes = nodes;
	}

	public Long getOuTypeId() {
		return ouTypeId;
	}

	public void setOuTypeId(Long ouTypeId) {
		this.ouTypeId = ouTypeId;
	}

	public Long getParentUnitId() {
		return parentUnitId;
	}

	public void setParentUnitId(Long parentUnitId) {
		this.parentUnitId = parentUnitId;
	}
	
	

	public String getOuTypeName() {
        return ouTypeName;
    }

    public void setOuTypeName(String ouTypeName) {
        this.ouTypeName = ouTypeName;
    }

    public Integer getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }
    
}
