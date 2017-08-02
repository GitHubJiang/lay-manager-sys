package com.lay.shop.greeston.tags;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.springframework.security.core.context.SecurityContextHolder;

import com.lay.shop.common.constants.AuthConstants;
import com.lay.shop.greeston.command.auth.UserDetailsCommand;

public class ListPrivilegeTag extends BasePrivilegeTag {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4955125801722454636L;

	
	
	@Override
	public int doStartTag() throws JspException {
		UserDetailsCommand udc=(UserDetailsCommand) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Map<String, List<String>> priMap=udc.getCommand().getPriFunMap();
		
		if(priMap!=null && !priMap.isEmpty()){
			//用户当前权限acl列表
			List<String> list=priMap.get(acl);
			if(list!=null){
				if(list.contains(AuthConstants.P_FUNCTION_TYPE_VIEW)||
					list.contains(AuthConstants.P_FUNCTION_TYPE_UPDATE)||
					list.contains(AuthConstants.P_FUNCTION_TYPE_DELETE)){
					return EVAL_BODY_INCLUDE;
				}
			}
		}
		return SKIP_BODY;
	}



	@Override
	protected String getType() {
		return null;
	}
	
}
