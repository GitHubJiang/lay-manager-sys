package com.lay.shop.greeston.tags;

import com.lay.shop.common.constants.AuthConstants;

public class RemovePrivilegeTag extends BasePrivilegeTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2092936049745445533L;

	@Override
	protected String getType() {
		return AuthConstants.P_FUNCTION_TYPE_DELETE;
	}


	
	
}
