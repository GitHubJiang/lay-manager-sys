package com.lay.shop.modules.sys.service;

import com.lay.shop.modules.sys.command.UserCommand;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 下午2:33:25
 * @since
 */
public interface UserService{
	
    /**登录接口*/
    UserCommand findUserByLoginNameAndEncryptedPassword(String loginName,String password);
    
}
