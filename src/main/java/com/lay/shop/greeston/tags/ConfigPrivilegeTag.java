package com.lay.shop.greeston.tags;

import com.lay.shop.common.constants.AuthConstants;

public class ConfigPrivilegeTag extends BasePrivilegeTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 292773538558150365L;

	@Override
	protected String getType() {
		return AuthConstants.P_FUNCTION_TYPE_CONFIG;
	}

	
	
	
}
