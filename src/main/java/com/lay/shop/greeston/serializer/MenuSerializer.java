package com.lay.shop.greeston.serializer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lay.shop.common.utils.SpringWebUtil;
import com.lay.shop.common.utils.Validator;
import com.lay.shop.greeston.command.auth.MenuCommand;

public class MenuSerializer extends JsonSerializer<MenuCommand> {
	
	@Override
	public void serialize(MenuCommand value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		jgen.writeStartObject();
		if (value.getIsGroup()) {
			jgen.writeStringField("icon", "icon-feather");
			jgen.writeObjectField("submenus", value.getChildList());
			jgen.writeStringField("url", "");
		} else {
			jgen.writeStringField("url", getContextPath() + value.getUrl());
		}
		
		if(value.getAppId() != null) {
			jgen.writeNumberField("appId", value.getAppId());
		}
		if(value.getId() != null) {
			jgen.writeNumberField("id", value.getId());
			jgen.writeNumberField("menuId", value.getId());
		}
		
		if(value.getName() != null) {
			jgen.writeStringField("text", value.getName());
			jgen.writeStringField("name", value.getName());
		}
		if(value.getAcl() != null) {
			jgen.writeStringField("acl", value.getAcl());
		}else{
		    jgen.writeStringField("acl", "");
		}
		if(value.getParentId() != null) {
			jgen.writeNumberField("parentId", value.getParentId());
		}else{
		    jgen.writeNumberField("parentId", null);
		}
		if(value.getChildList() != null) {
			jgen.writeObjectField("nodes", value.getChildList());
			jgen.writeStringField("icon", "icon-feather");
		}else{
		    jgen.writeObjectField("nodes", "");
		}
		if(Validator.isNotNullOrEmpty(value.getSortNo())) {
			jgen.writeNumberField("sortNo", value.getSortNo());
		}
		
		if(Validator.isNotNullOrEmpty(value.getLifecycle())) {
			jgen.writeNumberField("lifecycle", value.getLifecycle());
			jgen.writeStringField("class","treeview-lifecycle" + value.getLifecycle());
		}
        jgen.writeEndObject(); 
	}
	
	/***
	 * 
	 * @author Lay
	 * @return
	 * @since
	 */
	private String getContextPath() {
		HttpServletRequest request = SpringWebUtil.getRequest();
		String contextPath = "";
		if(request != null) {
			contextPath = request.getContextPath();
		}
		return contextPath;
	}
}
