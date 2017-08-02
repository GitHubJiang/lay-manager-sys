package com.lay.shop.greeston.tags;

import com.lay.shop.common.constants.AuthConstants;


public class ViewPrivilegeTag extends BasePrivilegeTag {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7269132933098553568L;

	@Override
	protected String getType() {
		return AuthConstants.P_FUNCTION_TYPE_VIEW;
	}
	
}
