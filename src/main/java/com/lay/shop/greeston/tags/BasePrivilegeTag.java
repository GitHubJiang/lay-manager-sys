package com.lay.shop.greeston.tags;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.springframework.security.core.context.SecurityContextHolder;

import com.lay.shop.greeston.command.auth.UserDetailsCommand;

public abstract class BasePrivilegeTag extends BaseTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8427540632720691959L;
	/**
	 * acl
	 */
	public String acl;
	
	@Override
	public int doStartTag() throws JspException {
		UserDetailsCommand udc=(UserDetailsCommand) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Map<String, List<String>> priMap=udc.getCommand().getPriFunMap();
		
		if(priMap!=null && !priMap.isEmpty()){
			//用户当前权限acl列表
			List<String> list=priMap.get(this.acl);
			if(list!=null){
				if(list.contains(this.getType())){
					return EVAL_BODY_INCLUDE;
				}
			}
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		
		return EVAL_PAGE;
	}

	public String getAcl() {
		return acl;
	}

	public void setAcl(String acl) {
		this.acl = acl;
	}

	protected abstract String getType();
}
