package com.lay.shop.greeston.tags;

import com.lay.shop.common.constants.AuthConstants;

public class OperatePrivilegeTag extends BasePrivilegeTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1491972391566301171L;

	@Override
	protected String getType() {
		return AuthConstants.P_FUNCTION_TYPE_OPERATE;
	}

	
	
	
}
