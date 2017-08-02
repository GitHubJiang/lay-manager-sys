package com.lay.shop.greeston.tags;

import com.lay.shop.common.constants.AuthConstants;

public class UpdatePrivilegeTag extends BasePrivilegeTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4296244569250493137L;

	@Override
	protected String getType() {
		return AuthConstants.P_FUNCTION_TYPE_UPDATE;
	}

	
	
	
}
