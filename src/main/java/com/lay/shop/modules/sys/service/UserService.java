package com.lay.shop.modules.sys.service;

import com.lay.shop.modules.sys.command.UserCommand;
import com.lay.shop.modules.sys.model.User;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 下午2:33:25
 * @since
 */
public interface UserService{
	
    void save(User user);
    
    UserCommand findUserByLoginNameAndEncryptedPassword(String loginName,String password);
    
}
