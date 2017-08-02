package com.lay.shop.greeston.tags;

import com.lay.shop.common.constants.AuthConstants;

public class AddPrivilegeTag extends BasePrivilegeTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7146546983095315203L;

	@Override
	protected String getType() {
		return AuthConstants.P_FUNCTION_TYPE_SAVE;
	}
	
}
