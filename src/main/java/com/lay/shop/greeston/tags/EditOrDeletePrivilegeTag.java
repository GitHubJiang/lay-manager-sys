package com.lay.shop.greeston.tags;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.springframework.security.core.context.SecurityContextHolder;

import com.lay.shop.common.constants.AuthConstants;
import com.lay.shop.greeston.command.auth.UserDetailsCommand;

public class EditOrDeletePrivilegeTag extends BasePrivilegeTag{

	private static final long serialVersionUID = 3893842140308339462L;

	@Override
	protected String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 控制当有编辑权限或删除权限其中至少一个时显示
	 * */
	@Override
	public int doStartTag() throws JspException {
		UserDetailsCommand udc=(UserDetailsCommand) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Map<String, List<String>> priMap=udc.getCommand().getPriFunMap();
		if(priMap!=null && !priMap.isEmpty()){
			List<String> list = priMap.get(this.acl);
			if(list != null){
				if(list.contains(AuthConstants.P_FUNCTION_TYPE_UPDATE) || list.contains(AuthConstants.P_FUNCTION_TYPE_DELETE)){
					return EVAL_BODY_INCLUDE;
				}
			}
		}
		return SKIP_BODY;
	}

}
